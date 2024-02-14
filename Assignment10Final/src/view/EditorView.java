package view;

/**
 * Simple interface for a view object.
 */
public interface EditorView {

  /**
   * Method to render a message to an appendable.
   * @param s - message to be appended.
   */
  void renderMessage(String s);
}
