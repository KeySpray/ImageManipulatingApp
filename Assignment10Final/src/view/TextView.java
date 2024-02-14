package view;

import java.io.IOException;

/**
 * Simple class that enables appending to custom appendable or to System.out.
 */
public class TextView implements EditorView {

  private final Appendable ap;

  /**
   * Constructor that takes in an appendable object as output destination.
   * @param ap - appendable object
   */
  public TextView(Appendable ap) {
    if (ap == null) {
      throw new IllegalArgumentException("Appendable cannot be null");
    }
    this.ap = ap;
  }

  /**
   * Constructor that takes in an appendable object as output destination.
   */
  public TextView() {
    this.ap = System.out;
  }

  /**
   * Simple method to render message to an appendable.
   * @param message - the desired message to append.
   */
  public void renderMessage(String message) {
    if (message == null) {
      throw new IllegalArgumentException("No message provided.");
    }

    try {
      if (message.endsWith("\n")) { // checks for message ending in new-line char
        ap.append(message);
      }

      ap.append(message).append("\n"); // appends new-line after message if not found
    }

    catch (IOException e) {
      throw new RuntimeException("Failed to append message", e);
    }
  }
}
