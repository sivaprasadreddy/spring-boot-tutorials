/**
 * 
 */
package com.sivalabs.blog.resources;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * @author Siva
 *
 */
public abstract class AbstractCollectionResource<E,D>
{
	private List<D> content;
	private long totalRecords;
	private int currentPage;
	private int pageSize;
	private boolean hasNextPage;
	private boolean hasPrevPage;

	public AbstractCollectionResource(Page<E> pageData)
	{
		this.content = this.populateDTOs(pageData.getContent());
		this.totalRecords = pageData.getTotalElements();
		this.currentPage = pageData.getNumber();
		this.pageSize = pageData.getSize();
		this.hasNextPage = pageData.hasNext();
		this.hasPrevPage = pageData.hasPrevious();
	}

	protected abstract List<D> populateDTOs(List<E> content);

	public List<D> getContent()
	{
		return content;
	}

	public void setContent(List<D> content)
	{
		this.content = content;
	}

	public long getTotalRecords()
	{
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords)
	{
		this.totalRecords = totalRecords;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public boolean isHasNextPage()
	{
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage)
	{
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPrevPage()
	{
		return hasPrevPage;
	}

	public void setHasPrevPage(boolean hasPrevPage)
	{
		this.hasPrevPage = hasPrevPage;
	}

}
