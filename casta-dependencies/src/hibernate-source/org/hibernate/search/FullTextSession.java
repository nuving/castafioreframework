/*
 * Copyright (C) 2007-2010 Castafiore
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
 //$Id: FullTextSession.java 15604 2008-11-21 13:58:05Z hardy.ferentschik $
package org.hibernate.search;

import java.io.Serializable;

import org.hibernate.classic.Session;

/**
 * Extends the Hibernate {@link Session} with fulltext search and indexing capabilities.
 *
 * @author Emmanuel Bernard
 */
public interface FullTextSession extends Session {
	
	/**
	 * Create a fulltext query on top of a native Lucene query returning the matching objects
	 * of type <code>entities</code> and their respective subclasses.
	 *
	 * @param luceneQuery The native Lucene query to be rn against the Lucene index.
	 * @param entities List of classes for type filtering. The query result will only return entities of
	 * the specified types and their respective subtype. If no class is specified no type filtering will take place.
	 *
	 * @return A <code>FullTextQuery</code> wrapping around the native Lucene wuery.
	 *
	 * @throws IllegalArgumentException if entityType is <code>null</code> or not a class or superclass annotated with <code>@Indexed</code>.
	 */
	FullTextQuery createFullTextQuery(org.apache.lucene.search.Query luceneQuery, Class<?>... entities);

	/**
	 * Force the (re)indexing of a given <b>managed</b> object.
	 * Indexation is batched per transaction: if a transaction is active, the operation
	 * will not affect the index at least until commit.
	 *
	 * @param entity The entity to index - must not be <code>null</code>.
	 *
	 * @throws IllegalArgumentException if entity is null or not an @Indexed entity
	 */
	<T> void index(T entity);

	/**
	 * @return the <code>SearchFactory</code> instance.
	 */
	SearchFactory getSearchFactory();

	/**
	 * Remove the entity with the type <code>entityType</code> and the identifier <code>id</code> from the index.
	 * If <code>id == null</code> all indexed entities of this type and its indexed subclasses are deleted. In this
	 * case this method behaves like {@link #purgeAll(Class)}.
	 *
	 * @param entityType The type of the entity to delete.
	 * @param id The id of the entity to delete.
	 *
	 * @throws IllegalArgumentException if entityType is <code>null</code> or not a class or superclass annotated with <code>@Indexed</code>.
	 */
	public <T> void purge(Class<T> entityType, Serializable id);

	/**
	 * Remove all entities from of particular class and all its subclasses from the index.
	 *
	 * @param entityType The class of the entities to remove.
	 *
	 * @throws IllegalArgumentException if entityType is <code>null</code> or not a class or superclass annotated with <code>@Indexed</code>.
	 */
	public <T> void purgeAll(Class<T> entityType);

	/**
	 * Flush all index changes forcing Hibernate Search to apply all changes to the index not waiting for the batch limit.
	 */
	public void flushToIndexes();
}