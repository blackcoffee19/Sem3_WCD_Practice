package vn.aptech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
	@Id
	@Column(name="bookCode")
	private String bookCode;
	@Column(name="title")
	private String title;
	@Column(name="price")
	private int price;
	@Column(name="publisher")
	private String publisher;
}
