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
package com.pkrete.locationservice.admin.controller;

import com.pkrete.locationservice.admin.editor.OwnerEditor;
import com.pkrete.locationservice.admin.editor.UserGroupEditor;
import com.pkrete.locationservice.admin.exception.ObjectNotFoundException;
import com.pkrete.locationservice.admin.model.owner.Owner;
import com.pkrete.locationservice.admin.model.user.User;
import com.pkrete.locationservice.admin.model.user.UserGroup;
import com.pkrete.locationservice.admin.model.user.UserInfo;
import com.pkrete.locationservice.admin.service.OwnersService;
import com.pkrete.locationservice.admin.service.UsersService;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * The {@link EditUserController EditUserController} handles the requests
 * that are related to adding a new user to the database. This class extends
 * SimpleFormController class.
 *
 * @author Petteri Kivimäki
 */
public class EditUserController extends SimpleFormController {

    private OwnersService ownersService;
    private UsersService usersService;
    private OwnerEditor ownerEditor;
    private UserGroupEditor userGroupEditor;

    public void setOwnersService(OwnersService ownersService) {
        this.ownersService = ownersService;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    public void setOwnerEditor(OwnerEditor ownerEditor) {
        this.ownerEditor = ownerEditor;
    }

    public void setUserGroupEditor(UserGroupEditor userGroupEditor) {
        this.userGroupEditor = userGroupEditor;
    }

    public EditUserController() {
        setCommandClass(UserInfo.class);
        setCommandName("user");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        UserInfo user = (UserInfo) command;
        String userId = request.getParameter("select_user");

        user.getUser().setUpdater(getUser(request).getUsername());
        // Updates only User
        if(!usersService.update(user.getUser())) {
            throw new Exception("Updating user failed.");
        }
        // Updates only UserInfor
        if(!usersService.update(user)) {
            throw new Exception("Updating user info failed.");
        }

        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.setAttribute("user", usersService.getUser(request.getRemoteUser()));

        return new ModelAndView("redirect:userowner.htm?select_user=" + userId);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(UserGroup.class, userGroupEditor);
        binder.registerCustomEditor(Owner.class, ownerEditor);
    }

    @Override
    protected java.util.Map referenceData(HttpServletRequest request) throws Exception {
        ModelMap modelMap = new ModelMap();
        modelMap.put("owners", ownersService.getOwners());
        modelMap.put("groups", UserGroup.values());
        return modelMap;
    }

    @Override
    /* Initialize the command object */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String id = request.getParameter("select_user");
        UserInfo info = usersService.getUserInfoByUsername(id);
        if (info == null) {
            throw new ObjectNotFoundException(new StringBuilder("User not found. {\"id\":").append(id).append("}").toString());
        }
        return info;
    }

    protected User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = usersService.getUser(request.getRemoteUser());
            session.setAttribute("user", user);
        }
        return user;
    }
}
