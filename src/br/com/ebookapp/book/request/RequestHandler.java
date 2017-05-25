package br.com.ebookapp.book.request;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.ebookapp.author.bean.AuthorBean;
import br.com.ebookapp.book.bean.BookBean;
import br.com.ebookapp.database.RequestConnection;
import br.com.ebookapp.publisher.bean.PublisherBean;
import br.com.ebookapp.subject.bean.SubjectBean;

public class RequestHandler {
	private ResultSet resultSet = null;
	private String sql = "";
	private List<BookBean> list = null;
	private BookBean book;
	
	public boolean createBook(BookBean book) {
		if (book.getAuthor().getName().length() > 0) {
			book.setAuthor(new br.com.ebookapp.author.request.RequestHandler().getAuthor(book.getAuthor().getName()));
		} else {
			book.setAuthor(new br.com.ebookapp.author.request.RequestHandler().createAuthorFromName(book.getAuthor().getName()));
		}
		
		if (book.getPublisher().getName().length() > 0) {
			book.setPublisher(new br.com.ebookapp.publisher.request.RequestHandler().getPublisher(book.getPublisher().getName()));
		} else {
			book.setPublisher(new br.com.ebookapp.publisher.request.RequestHandler().createPublisherFromName(book.getPublisher().getName()));
		}
		
		if (book.getSubject().getName().length() > 0) {
			book.setSubject(new br.com.ebookapp.subject.request.RequestHandler().getSubject(book.getSubject().getName()));
		} else {
			book.setSubject(new br.com.ebookapp.subject.request.RequestHandler().createSubjectFromName(book.getSubject().getName()));
		}
		
		this.sql = "INSERT INTO BOOK"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			pstmt.setInt(1, this.getIdFromLastIndex());
			pstmt.setString(2,  book.getName());
			pstmt.setString(3, book.getDescription());
			pstmt.setDouble(4, book.getPrice());
			pstmt.setDouble(5, book.getDiscount());
			pstmt.setInt(6, book.getStock());
			pstmt.setInt(7, book.getEdition());
			pstmt.setInt(8, book.getAuthor().getAuthor_id());
			pstmt.setInt(9, book.getPublisher().getPublisher_id());
			pstmt.setInt(10, book.getSubject().getSubject_id());
			pstmt.setString(11, book.getImage());
			pstmt.setDate(12, new Date(Calendar.getInstance().getTimeInMillis()));
			return pstmt.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("CREATE BOOK " + e.getMessage());
		}
		return false;
	}
	
	public List<BookBean> getBook() {
		this.sql = "SELECT 	book.book_id, "
				+ "			book.name, "
				+ "			book.description, "
				+ "			book.price, "
				+ "			book.discount, "
				+ "			book.stock, "
				+ "			book.edition, "
				+ "			author.author_id, "
				+ "			author.name,"
				+ "			publisher."
				+ "			publisher_id, "
				+ "			publisher.name, "
				+ "			publisher.description, "
				+ "			subject.subject_id, "
				+ "			subject.name,"
				+ "			book.image,"
				+ "			book.release_date"
				+ " FROM 	BOOK book"
				+ " JOIN 	AUTHOR author"
				+ "	ON		(book.author_author_id = author.author_id)"
				+ "	JOIN	PUBLISHER publisher"
				+ "	ON 		(book.publisher_publisher_id = publisher.publisher_id)"
				+ "	JOIN	SUBJECT subject"
				+ " ON		(book.subject_subject_id = subject.subject_id)";
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = pstmt.executeQuery();
			this.list = new ArrayList<BookBean>();
			while (this.resultSet.next()) {
				this.book = new BookBean();
				this.book.setBook_id(this.resultSet.getInt(1));
				this.book.setName(this.resultSet.getString(2));
				this.book.setDescription(this.resultSet.getString(3));
				this.book.setPrice(this.resultSet.getDouble(4));
				this.book.setDiscount(this.resultSet.getDouble(5));
				this.book.setStock(this.resultSet.getInt(6));
				this.book.setEdition(this.resultSet.getInt(7));
				this.book.setAuthor(new AuthorBean(this.resultSet.getInt(8), this.resultSet.getString(9)));
				this.book.setPublisher(new PublisherBean(this.resultSet.getInt(10), this.resultSet.getString(11), this.resultSet.getString(12)));
				this.book.setSubject(new SubjectBean(this.resultSet.getInt(13), this.resultSet.getString(14)));
				this.book.setImage(this.resultSet.getString(15));
				this.book.setReleaseDate(this.resultSet.getDate(16));
				this.list.add(this.book);
			}
			return this.list;
		} catch (Exception e) {
			System.out.println("GET BOOK " + e.getMessage());
		}
		return new ArrayList<BookBean>();
	}
	
	public BookBean getBook(int id) {
		this.sql = "SELECT 	book.book_id, "
				+ "			book.name, "
				+ "			book.description, "
				+ "			book.price, "
				+ "			book.discount, "
				+ "			book.stock, "
				+ "			book.edition, "
				+ "			author.author_id, "
				+ "			author.name,"
				+ "			publisher."
				+ "			publisher_id, "
				+ "			publisher.name, "
				+ "			publisher.description, "
				+ "			subject.subject_id, "
				+ "			subject.name,"
				+ "			book.image,"
				+ "			book.release_date"
				+ " FROM 	BOOK book"
				+ " JOIN 	AUTHOR author"
				+ "	ON		(book.author_author_id = author.author_id)"
				+ "	JOIN	PUBLISHER publisher"
				+ "	ON 		(book.publisher_publisher_id = publisher.publisher_id)"
				+ "	JOIN	SUBJECT subject"
				+ " ON		(book.subject_subject_id = subject.subject_id)"
				+ "	WHERE 	book.book_id = " + id;
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = pstmt.executeQuery();
			if (this.resultSet.next()) {
				this.book = new BookBean();
				this.book.setBook_id(this.resultSet.getInt(1));
				this.book.setName(this.resultSet.getString(2));
				this.book.setDescription(this.resultSet.getString(3));
				this.book.setPrice(this.resultSet.getDouble(4));
				this.book.setDiscount(this.resultSet.getDouble(5));
				this.book.setStock(this.resultSet.getInt(6));
				this.book.setEdition(this.resultSet.getInt(7));
				this.book.setAuthor(new AuthorBean(this.resultSet.getInt(8), this.resultSet.getString(9)));
				this.book.setPublisher(new PublisherBean(this.resultSet.getInt(10), this.resultSet.getString(11), this.resultSet.getString(12)));
				this.book.setSubject(new SubjectBean(this.resultSet.getInt(13), this.resultSet.getString(14)));
				this.book.setImage(this.resultSet.getString(15));
				this.book.setReleaseDate(this.resultSet.getDate(16));
			}
			return this.book;
		} catch (Exception e) {
			System.out.println("GET BOOK BY ID " + e.getMessage());
		}
		return new BookBean();
	}
	
	public boolean updateBook(BookBean book, int id) {
		this.sql = "UPDATE 	BOOK SET book.name = ?,"
				+ "			book.description = ?,"
				+ "			book.price = ?,"
				+ "			book.discount = ?,"
				+ "			book.stock = ?,"
				+ "			book.edition = ?,"
				+ "			book.author_author_id = ?, "
				+ "			book.publisher_publisher_id = ?,"
				+ "			book.subject_subject_id = ?,"
				+ "			book.image = ?"
				+ " WHERE	book.book_id = " + id;
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			pstmt.setString(1, book.getName());
			pstmt.setString(2, book.getDescription());
			pstmt.setDouble(3, book.getPrice());
			pstmt.setDouble(4, book.getDiscount());
			pstmt.setInt(5, book.getStock());
			pstmt.setInt(6, book.getEdition());
			pstmt.setInt(7, book.getAuthor().getAuthor_id());
			pstmt.setInt(8, book.getPublisher().getPublisher_id());
			pstmt.setInt(9, book.getSubject().getSubject_id());
			pstmt.setString(10, book.getImage());
			return pstmt.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("UPDATE BOOK " + e.getMessage());
		}
		return false;
	}
	
	public List<BookBean> getBook(String filter) {
		this.sql = "SELECT 	book.book_id, "
				+ "			book.name, "
				+ "			book.description, "
				+ "			book.price, "
				+ "			book.discount, "
				+ "			book.stock, "
				+ "			book.edition, "
				+ "			author.author_id, "
				+ "			author.name,"
				+ "			publisher."
				+ "			publisher_id, "
				+ "			publisher.name, "
				+ "			publisher.description, "
				+ "			subject.subject_id, "
				+ "			subject.name,"
				+ "			book.image,"
				+ "			book.release_date"
				+ " FROM 	BOOK book"
				+ " JOIN 	AUTHOR author"
				+ "	ON		(book.author_author_id = author.author_id)"
				+ "	JOIN	PUBLISHER publisher"
				+ "	ON 		(book.publisher_publisher_id = publisher.publisher_id)"
				+ "	JOIN	SUBJECT subject"
				+ " ON		(book.subject_subject_id = subject.subject_id)"
				+ " WHERE 	book.name 			LIKE '%" + filter + "%'"
				+ " OR		book.description 	LIKE '%" + filter + "%'"
				+ " OR 		author.name			LIKE '%" + filter + "%'"
				+ " OR		publisher.name 		LIKE '%" + filter + "%'"
				+ " OR 		publisher.description	LIKE '%" + filter + "%'"
				+ " OR 		subject.name		LIKE '%" + filter + "%'";
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = pstmt.executeQuery();
			this.list = new ArrayList<BookBean>();
			while (this.resultSet.next()) {
				this.book = new BookBean();
				this.book.setBook_id(this.resultSet.getInt(1));
				this.book.setName(this.resultSet.getString(2));
				this.book.setDescription(this.resultSet.getString(3));
				this.book.setPrice(this.resultSet.getDouble(4));
				this.book.setDiscount(this.resultSet.getDouble(5));
				this.book.setStock(this.resultSet.getInt(6));
				this.book.setEdition(this.resultSet.getInt(7));
				this.book.setAuthor(new AuthorBean(this.resultSet.getInt(8), this.resultSet.getString(9)));
				this.book.setPublisher(new PublisherBean(this.resultSet.getInt(10), this.resultSet.getString(11), this.resultSet.getString(12)));
				this.book.setSubject(new SubjectBean(this.resultSet.getInt(13), this.resultSet.getString(14)));
				this.book.setImage(this.resultSet.getString(15));
				this.book.setReleaseDate(this.resultSet.getDate(16));
				this.list.add(this.book);
			}
			return this.list;
		} catch (Exception e) {
			System.out.println("GET BOOK BY ID " + e.getMessage());
		}
		return new ArrayList<BookBean>();
	}
	
	public List<BookBean> getBookFilteredByAdvancedSearch(List<String> map) {
		String searchField = "";
		if (map.size() > 0) 
			for (String search : map) {
				if (searchField.length() == 0) 
					searchField += " WHERE " + search;
				else
					searchField += " OR " + search;
			}
		this.sql = "SELECT 	book.book_id, "
				+ "			book.name, "
				+ "			book.description, "
				+ "			book.price, "
				+ "			book.discount, "
				+ "			book.stock, "
				+ "			book.edition, "
				+ "			author.author_id, "
				+ "			author.name,"
				+ "			publisher."
				+ "			publisher_id, "
				+ "			publisher.name, "
				+ "			publisher.description, "
				+ "			subject.subject_id, "
				+ "			subject.name,"
				+ "			book.image, "
				+ "			book.release_date"
				+ " FROM 	BOOK book"
				+ " JOIN 	AUTHOR author"
				+ "	ON		(book.author_author_id = author.author_id)"
				+ "	JOIN	PUBLISHER publisher"
				+ "	ON 		(book.publisher_publisher_id = publisher.publisher_id)"
				+ "	JOIN	SUBJECT subject"
				+ " ON		(book.subject_subject_id = subject.subject_id)"
				+ searchField;
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = pstmt.executeQuery();
			this.list = new ArrayList<BookBean>();
			while (this.resultSet.next()) {
				this.book = new BookBean();
				this.book.setBook_id(this.resultSet.getInt(1));
				this.book.setName(this.resultSet.getString(2));
				this.book.setDescription(this.resultSet.getString(3));
				this.book.setPrice(this.resultSet.getDouble(4));
				this.book.setDiscount(this.resultSet.getDouble(5));
				this.book.setStock(this.resultSet.getInt(6));
				this.book.setEdition(this.resultSet.getInt(7));
				this.book.setAuthor(new AuthorBean(this.resultSet.getInt(8), this.resultSet.getString(9)));
				this.book.setPublisher(new PublisherBean(this.resultSet.getInt(10), this.resultSet.getString(11), this.resultSet.getString(12)));
				this.book.setSubject(new SubjectBean(this.resultSet.getInt(13), this.resultSet.getString(14)));
				this.book.setImage(this.resultSet.getString(15));
				this.book.setReleaseDate(this.resultSet.getDate(16));
				this.list.add(this.book);
			}
			return this.list;
		} catch (Exception e) {
			System.out.println("GET BOOK BY ID " + e.getMessage());
		}
		return new ArrayList<BookBean>();
	}
	
	public List<BookBean> getBookWithDiscount() {
		this.sql = "SELECT 	book.book_id, "
				+ "			book.name, "
				+ "			book.description, "
				+ "			book.price, "
				+ "			book.discount, "
				+ "			book.stock, "
				+ "			book.edition, "
				+ "			author.author_id, "
				+ "			author.name,"
				+ "			publisher."
				+ "			publisher_id, "
				+ "			publisher.name, "
				+ "			publisher.description, "
				+ "			subject.subject_id, "
				+ "			subject.name,"
				+ "			book.image,"
				+ "			book.release_date"
				+ " FROM 	BOOK book"
				+ " JOIN 	AUTHOR author"
				+ "	ON		(book.author_author_id = author.author_id)"
				+ "	JOIN	PUBLISHER publisher"
				+ "	ON 		(book.publisher_publisher_id = publisher.publisher_id)"
				+ "	JOIN	SUBJECT subject"
				+ " ON		(book.subject_subject_id = subject.subject_id)"
				+ " WHERE 	book.discount > 0";
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = pstmt.executeQuery();
			this.list = new ArrayList<BookBean>();
			while (this.resultSet.next()) {
				this.book = new BookBean();
				this.book.setBook_id(this.resultSet.getInt(1));
				this.book.setName(this.resultSet.getString(2));
				this.book.setDescription(this.resultSet.getString(3));
				this.book.setPrice(this.resultSet.getDouble(4));
				this.book.setDiscount(this.resultSet.getDouble(5));
				this.book.setStock(this.resultSet.getInt(6));
				this.book.setEdition(this.resultSet.getInt(7));
				this.book.setAuthor(new AuthorBean(this.resultSet.getInt(8), this.resultSet.getString(9)));
				this.book.setPublisher(new PublisherBean(this.resultSet.getInt(10), this.resultSet.getString(11), this.resultSet.getString(12)));
				this.book.setSubject(new SubjectBean(this.resultSet.getInt(13), this.resultSet.getString(14)));
				this.book.setImage(this.resultSet.getString(15));
				this.book.setReleaseDate(this.resultSet.getDate(16));
				this.list.add(this.book);
			}
			return this.list;
		} catch (Exception e) {
			System.out.println("GET BOOK " + e.getMessage());
		}
		return new ArrayList<BookBean>();
	}
	
	public boolean deleteBook(int id) {
		this.sql = "DELETE 	FROM BOOK"
				+ " WHERE 	book_id = " + id;
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			return pstmt.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("DELETE BOOK " + e.getMessage());
		}
		return false;
	}
	
	private int getIdFromLastIndex() {
		this.sql = "SELECT MAX(book_id) FROM BOOK;";
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = pstmt.executeQuery();
			if (this.resultSet.next())
				return this.resultSet.getInt(1)+1;
		} catch (Exception e) {
			System.out.println("GET MAX INDEX " + e.getMessage());
		}
		return 999;
	}
}
