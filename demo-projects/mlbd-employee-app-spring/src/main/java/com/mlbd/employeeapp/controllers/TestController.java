package com.mlbd.employeeapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mlbd.employeeapp.config.CurrentUser;
import com.mlbd.employeeapp.config.CurrentUserProvider;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  private CurrentUserProvider currentUserProvider;

  @RequestMapping(value = "/anonymous", method = RequestMethod.GET)
  public ResponseEntity<String> getAnonymous() {
    return new ResponseEntity<>("Hello Anonymous", HttpStatus.OK);
  }

  @PreAuthorize("hasAuthority('ROLE_app-user')")
  @RequestMapping(value = "/user", method = RequestMethod.GET)
  public ResponseEntity<CurrentUser> getUser(@RequestHeader String Authorization) {
    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    return new ResponseEntity<>(currentUser, HttpStatus.OK);
  }

  @PreAuthorize("hasAuthority('ROLE_app-admin')")
  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public ResponseEntity<CurrentUser> getAdmin(@RequestHeader String Authorization) {
    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    return new ResponseEntity<>(currentUser, HttpStatus.OK);
  }
}
