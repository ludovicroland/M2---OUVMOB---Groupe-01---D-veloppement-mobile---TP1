package fr.efrei.tp1.service;

import java.util.List;

import fr.efrei.tp1.bo.User;

//Interface used in order to implement correctly the repository pattern
public interface IUserService
{

  /**
   * Get all users
   *
   * @return {@link List}
   */
  List<User> getUsers();

  /**
   * Deletes an user
   *
   * @param user
   */
  void deleteUser(User user);

  /**
   * Add an user
   *
   * @param user
   */
  void addUser(User user);

  /**
   * Get all users sorted by name
   *
   * @return {@link List}
   */
  List<User> sortUsersByName();
}
