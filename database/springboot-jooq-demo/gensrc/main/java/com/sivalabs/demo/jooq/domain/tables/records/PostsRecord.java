/**
 * This class is generated by jOOQ
 */
package com.sivalabs.demo.jooq.domain.tables.records;


import com.sivalabs.demo.jooq.domain.tables.Posts;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PostsRecord extends UpdatableRecordImpl<PostsRecord> implements Record4<Integer, String, String, Timestamp> {

	private static final long serialVersionUID = -1319080636;

	/**
	 * Setter for <code>PUBLIC.POSTS.ID</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>PUBLIC.POSTS.ID</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>PUBLIC.POSTS.TITLE</code>.
	 */
	public void setTitle(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>PUBLIC.POSTS.TITLE</code>.
	 */
	public String getTitle() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>PUBLIC.POSTS.CONTENT</code>.
	 */
	public void setContent(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>PUBLIC.POSTS.CONTENT</code>.
	 */
	public String getContent() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>PUBLIC.POSTS.CREATED_ON</code>.
	 */
	public void setCreatedOn(Timestamp value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>PUBLIC.POSTS.CREATED_ON</code>.
	 */
	public Timestamp getCreatedOn() {
		return (Timestamp) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, String, String, Timestamp> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, String, String, Timestamp> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Posts.POSTS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Posts.POSTS.TITLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Posts.POSTS.CONTENT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field4() {
		return Posts.POSTS.CREATED_ON;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getTitle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getContent();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value4() {
		return getCreatedOn();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PostsRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PostsRecord value2(String value) {
		setTitle(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PostsRecord value3(String value) {
		setContent(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PostsRecord value4(Timestamp value) {
		setCreatedOn(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PostsRecord values(Integer value1, String value2, String value3, Timestamp value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached PostsRecord
	 */
	public PostsRecord() {
		super(Posts.POSTS);
	}

	/**
	 * Create a detached, initialised PostsRecord
	 */
	public PostsRecord(Integer id, String title, String content, Timestamp createdOn) {
		super(Posts.POSTS);

		setValue(0, id);
		setValue(1, title);
		setValue(2, content);
		setValue(3, createdOn);
	}
}