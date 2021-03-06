/**
 * This file is part of Location Service :: Admin. Copyright (C) 2014 Petteri
 * Kivimäki
 *
 * Location Service :: Admin is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Location Service :: Admin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Location Service :: Admin. If not, see <http://www.gnu.org/licenses/>.
 */
package com.pkrete.locationservice.admin.io;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Map;

/**
 * DirectoryService interface defines service interface for managing
 * directories. It defines the necessary methods for managing directories in the
 * file system. Classes implementing this interface must must implement all the
 * methods defined here.
 *
 * @author Petteri Kivimäki
 */
public interface DirectoryService {

    List<File> getSubDirectories(String basePath);

    List<String> getSubDirectoriesStr(String basePath);

    List<File> getFiles(String dir);

    List<String> getFilesStr(String dir);

    List<File> getList(String dir, FileFilter filter);

    List<String> getListStr(String dir, FileFilter filter);

    boolean rename(String oldDir, String newDir);

    boolean add(String dir);

    boolean delete(String dir, boolean mustBeEmpty);

    boolean exists(String dir);

    boolean isEmpty(String dir);

    boolean isEmpty(String dir, Map<String, Boolean> ignoredFiles);
}
