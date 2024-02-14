package controller;

/**
 * Simple interface to ensure commands adhere to same object type.
 */
public interface Command {

  void execute(String[] args);
}
