package br.com.ebookapp.book.request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ebookapp.author.bean.AuthorBean;
import br.com.ebookapp.book.bean.BookBean;
import br.com.ebookapp.database.RequestConnection;
import br.com.ebookapp.publisher.bean.PublisherBean;
import br.com.ebookapp.subject.bean.SubjectBean;

public class RequestHandler {
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String sql = "";
	private List<BookBean> list = null;
	private BookBean book;
	
	public boolean createBook(BookBean book) {
		this.sql = "INSERT INTO BOOK"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			this.preparedStatement.setInt(1, this.getIdFromLastIndex());
			this.preparedStatement.setString(2,  book.getName());
			this.preparedStatement.setString(3, book.getDescription());
			this.preparedStatement.setDouble(4, book.getPrice());
			this.preparedStatement.setDouble(5, book.getDiscount());
			this.preparedStatement.setInt(6, book.getStock());
			this.preparedStatement.setInt(7, book.getEdition());
			this.preparedStatement.setInt(8, book.getAuthor().getAuthor_id());
			this.preparedStatement.setInt(9, book.getPublisher().getPublisher_id());
			this.preparedStatement.setInt(10, book.getSubject().getSubject_id());
			return this.preparedStatement.execute();
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
				+ "			subject.name"
				+ " FROM 	BOOK book"
				+ " JOIN 	AUTHOR author"
				+ "	ON		(book.author_author_id = author.author_id)"
				+ "	JOIN	PUBLISHER publisher"
				+ "	ON 		(book.publisher_publisher_id = publisher.publisher_id)"
				+ "	JOIN	SUBJECT subject"
				+ " ON		(book.subject_subject_id = subject.subject_id)";
		try {
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			this.resultSet = this.preparedStatement.executeQuery();
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
				this.list.add(this.book);
			}
			return this.list;
		} catch (Exception e) {
			System.out.println("GET BOOK " + e.getMessage());
		}
		return null;
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
				+ "			subject.name"
				+ " FROM 	BOOK book"
				+ " JOIN 	AUTHOR author"
				+ "	ON		(book.author_author_id = author.author_id)"
				+ "	JOIN	PUBLISHER publisher"
				+ "	ON 		(book.publisher_publisher_id = publisher.publisher_id)"
				+ "	JOIN	SUBJECT subject"
				+ " ON		(book.subject_subject_id = subject.subject_id)"
				+ "	WHERE 	book.book_id = " + id;
		try {
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			this.resultSet = this.preparedStatement.executeQuery();
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
			}
			return this.book;
		} catch (Exception e) {
			System.out.println("GET BOOK BY ID " + e.getMessage());
		}
		return null;
	}
	
	public boolean updateBook(BookBean book, int id) {
		this.sql = "UPDATE 	SET book.name = ?,"
				+ "			book.description = ?,"
				+ "			book.price = ?,"
				+ "			book.discount = ?,"
				+ "			book.stock = ?,"
				+ "			book.edition = ?,"
				+ "			book.author_author_id = ?, "
				+ "			book.publisher_publisher_id = ?,"
				+ "			book.subject_subject_id = ?"
				+ " WHERE	book.book_id = " + id;
		try {
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			this.preparedStatement.setString(1, book.getName());
			this.preparedStatement.setString(2, book.getDescription());
			this.preparedStatement.setDouble(3, book.getPrice());
			this.preparedStatement.setDouble(4, book.getDiscount());
			this.preparedStatement.setInt(5, book.getStock());
			this.preparedStatement.setInt(6, book.getEdition());
			this.preparedStatement.setInt(7, book.getAuthor().getAuthor_id());
			this.preparedStatement.setInt(8, book.getPublisher().getPublisher_id());
			this.preparedStatement.setInt(9, book.getSubject().getSubject_id());
			return this.preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("UPDATE BOOK " + e.getMessage());
		}
		return false;
	}
	
	public boolean deleteBook(int id) {
		this.sql = "DELETE 	FROM BOOK"
				+ " WHERE 	book_id = " + id;
		try {
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			return this.preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("DELETE BOOK " + e.getMessage());
		}
		return false;
	}
	
	private int getIdFromLastIndex() {
		this.sql = "SELECT MAX(book_id) FROM BOOK;";
		try {
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			this.resultSet = this.preparedStatement.executeQuery();
			if (this.resultSet.next())
				return this.resultSet.getInt(1);
		} catch (Exception e) {
			System.out.println("GET MAX INDEX " + e.getMessage());
		}
		return 999;
	}
}
