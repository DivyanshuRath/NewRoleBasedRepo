package com.deloitte.newproject.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This is an entity class.
 * An entity class is a Java class that represents a table in a database. An entity class is typically annotated with the @Entity annotation and includes fields that correspond to columns in the table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer implements Serializable{

// define fields

	/**
	 * The id of the resource
	 * The @Id and @GeneratedValue annotations are used to specify that the id field is the primary key for the table and that the value should be generated automatically.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CustomerId")
	private Long customerId;

	/**
	 * The name of the resource
	 */
	@Column(name="CustomerName")
	private String customerName;

}