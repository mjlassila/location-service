/**
 * This file is part of Location Service :: Endpoint.
 * Copyright (C) 2014 Petteri Kivimäki
 *
 * Location Service :: Endpoint is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Location Service :: Endpoint is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Location Service :: Endpoint. If not, see <http://www.gnu.org/licenses/>.
 */
package com.pkrete.locationservice.endpoint.model.search;

/**
 * This enum represents the type of the search that is done through
 * the Exporter servlet.
 * 
 * @author Petteri Kivimäki
 */
public enum SearchType {

    CALLNO, CODE, ID, DESC, NOTE, SUBJECT, STAFFNOTE1, STAFFNOTE2, FLOOR,
    SHELF, ALL;
}
