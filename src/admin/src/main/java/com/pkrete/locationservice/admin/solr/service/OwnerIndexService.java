/**
 * This file is part of Location Service :: Admin.
 * Copyright (C) 2014 Petteri Kivimäki
 *
 * Location Service :: Admin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Location Service :: Admin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Location Service :: Admin. If not, see <http://www.gnu.org/licenses/>.
 */
package com.pkrete.locationservice.admin.solr.service;

import com.pkrete.locationservice.admin.model.owner.Owner;
import com.pkrete.locationservice.admin.solr.model.OwnerDocument;
import java.util.List;

/**
 * This interface defines methods for adding, updating, deleting and 
 * searching Owners from external search index.
 * 
 * @author Petteri Kivimäki
 */
public interface OwnerIndexService {
    
    public List<OwnerDocument> find();

    public OwnerDocument find(int id);

    public List<OwnerDocument> find(String ownerCode);

    public boolean save(Owner document);

    public boolean delete(Integer id);

    public boolean deleteAll();
}