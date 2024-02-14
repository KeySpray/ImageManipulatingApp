package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.List;

/**
 * An interactive/GUI view for the program.
 */
public class GraphicalView extends JFrame implements ActionListener {

  private final JLabel displayText;
  JPanel buttonHolder = new JPanel();
  JPanel canvasHolder = new JPanel();
  JPanel imageList = new JPanel();
  private final List<ViewListener> listenersToNotify;
  private String activeImageKey;
  private final ImageCanvas originalImageCanvas;

  /**
   * A no argument constructor for the GUI.
   */
  public GraphicalView() {

    this.listenersToNotify = new ArrayList<>();

    // main frame set up
    BorderLayout mainGUI = new BorderLayout();
    Container mainContainer = getContentPane();
    mainContainer.setLayout(mainGUI);

    // load button creation
    JButton load = new JButton("Load Image");
    load.setActionCommand("load");

    // save button creation
    JButton save = new JButton("Save Image");
    save.setActionCommand("save");

    // brighten button creation
    JButton brighten = new JButton("Brighten Transformation");
    brighten.setActionCommand("brighten");

    // darken button creation
    JButton darken = new JButton("Darken Transformation");
    darken.setActionCommand("darken");

    // blur button creation
    JButton blur = new JButton("Blur Transformation");
    blur.setActionCommand("blur");

    // sharpen button creation
    JButton sharpen = new JButton("Sharpen Transformation");
    sharpen.setActionCommand("sharpen");

    // red component transformation button creation
    JButton redTransform = new JButton("Red Component Transformation");
    redTransform.setActionCommand("red");

    // green component transformation button creation
    JButton greenTransform = new JButton("Green Component Transformation");
    greenTransform.setActionCommand("green");

    // blue component transformation button creation
    JButton blueTransform = new JButton("Blue Component Transformation");
    blueTransform.setActionCommand("blue");

    // intensity transformation button creation
    JButton intensity = new JButton("Intensity Transformation");
    intensity.setActionCommand("intensity");

    // luma transformation button creation
    JButton luma = new JButton("Luma Transformation");
    luma.setActionCommand("luma");

    // value component transformation button creation
    JButton valueComp = new JButton("Value Component Transformation");
    valueComp.setActionCommand("valueComp");

    // sepia transformation button creation
    JButton sepia = new JButton("Sepia Transformation");
    sepia.setActionCommand("sepia");

    // greyscale transformation button creation
    JButton greyscale = new JButton("Greyscale Transformation");
    greyscale.setActionCommand("greyscale");

    // add listener for buttons
    save.addActionListener(this);
    load.addActionListener(this);
    brighten.addActionListener(this);
    darken.addActionListener(this);
    blur.addActionListener(this);
    sharpen.addActionListener(this);
    redTransform.addActionListener(this);
    greenTransform.addActionListener(this);
    blueTransform.addActionListener(this);
    intensity.addActionListener(this);
    luma.addActionListener(this);
    valueComp.addActionListener(this);
    sepia.addActionListener(this);
    greyscale.addActionListener(this);

    // button grid set up
    GridBagLayout buttonGrid = new GridBagLayout();
    this.buttonHolder.setLayout(buttonGrid);
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 40; // makes load buttons taller
    c.gridx = 0; // sets row of grid for following button
    c.gridy = 0; // sets column of grid for following button
    this.buttonHolder.add(load, c); // adds load button to button grid
    c.ipady = 0; // resets padding variable for following buttons
    c.gridy = 1; // moves to next row
    // following code follows same structure as above
    this.buttonHolder.add(brighten, c);
    c.gridy = 2;
    this.buttonHolder.add(darken, c);
    c.gridy = 3;
    this.buttonHolder.add(blur, c);
    c.gridy = 4;
    this.buttonHolder.add(sharpen, c);
    c.gridy = 5;
    this.buttonHolder.add(redTransform, c);
    c.gridy =6;
    this.buttonHolder.add(greenTransform, c);
    c.gridy = 7;
    this.buttonHolder.add(blueTransform, c);
    c.gridy = 8;
    this.buttonHolder.add(intensity, c);
    c.gridy = 9;
    this.buttonHolder.add(luma, c);
    c.gridy = 10;
    this.buttonHolder.add(valueComp, c);
    c.gridy = 11;
    this.buttonHolder.add(sepia, c);
    c.gridy = 12;
    this.buttonHolder.add(greyscale, c);
    c.gridy = 13;
    c.ipady = 40;
    this.buttonHolder.add(save, c);
    JScrollPane buttonPane = new JScrollPane(this.buttonHolder); // adds scroll feature


    // image panel set up
    JPanel originalImageHolder = new JPanel(); // creates new panel
    originalImageHolder.setLayout(new BorderLayout()); // sets layout
    originalImageHolder.add(new JLabel("Active Image"), BorderLayout.NORTH); // adds header
    this.originalImageCanvas = new ImageCanvas(); // creates canvas
    //this.originalImageCanvas.setBackground(Color.BLACK);
    JScrollPane originalPane = new JScrollPane(this.originalImageCanvas); // adds scroll feature
    originalImageHolder.add(originalPane, BorderLayout.CENTER);

    this.canvasHolder.setLayout(new GridLayout(1, 1)); // sets layout for canvasses
    this.canvasHolder.add(originalImageHolder); // adds image panel

    this.displayText = new JLabel("Messages will be displayed here."); // adds message display

    // loaded image list set up
    this.imageList.setLayout(new BoxLayout(imageList, BoxLayout.Y_AXIS)); // sets layout
    JLabel imageListLabel = new JLabel("Loaded Images"); // creates header
    imageList.add(imageListLabel); // adds label to panel
    JScrollPane imageListPane = new JScrollPane(this.imageList); // adds scroll feature

    // main panel set up
    mainContainer.add(buttonPane, BorderLayout.WEST); // adds buttons to main container
    mainContainer.add(canvasHolder, BorderLayout.CENTER); // adds canvas
    mainContainer.add(imageListPane, BorderLayout.EAST); // adds image list
    mainContainer.add(displayText, BorderLayout.PAGE_END); // adds display text to bottom
  }

  /**
   * A method to add listeners to the list of listeners to notify.
   * @param listener - the listener to be added/notified of events
   */
  public void addViewListener(ViewListener listener) {
    this.listenersToNotify.add(listener); // adds passed listener to list to notify
  }

  /**
   * A method to emit a load event.
   */
  private void emitLoadEvent() {
    String filePath = getFilePath(false); // indicates file chooser in open mode
    // Prompts user for input and displays helpful message
    String fileName = getUserInput("Please enter a nickname for this image.");
    // notifies listener to handle a load event
    for ( ViewListener listener : listenersToNotify) {
      listener.handleLoadEvent(filePath, fileName);
    }
    // adds button to display image to image list if args not null
    if (filePath != null && fileName != null) {
      updateImageList("Open: " + fileName);
    }
  }

  /**
   * A method to emit a display image event. Requires controller to convert image to BufferedImage.
   * @param imageKey - the name of the file as loaded/stored in database
   */
  private void emitDisplayImageEvent(String imageKey) {
    this.activeImageKey = imageKey; // updates activeImageKey attribute
    BufferedImage image = null; // initializes variable
    // notifies listener list to handle display event
    for (ViewListener listener : listenersToNotify) {
      image = listener.handleDisplayImageEvent(this.activeImageKey);
    }

    this.originalImageCanvas.setImage(image); // sets & draws image on canvas
  }

  /**
   * A method to emit a brightening event.
   */
  private void emitBrightenEvent() {
    for ( ViewListener listener : listenersToNotify) {
      listener.handleBrightenEvent(this.activeImageKey,
        // fetches brightening factor as string to be parsed by controller
        getUserInput("Enter a brightening factor between 0 and 255."));
    }

    emitDisplayImageEvent(this.activeImageKey); // re-displays the modified image
  }

  // following methods follow same structure as above
  private void emitDarkenEvent() {
    for ( ViewListener listener : listenersToNotify) {
      listener.handleDarkenEvent(this.activeImageKey,
        getUserInput("Enter a darkening factor between 0 and 255."));
    }

    emitDisplayImageEvent(this.activeImageKey);
  }
  private void emitColorEvent(String filter) {
    for ( ViewListener listener : listenersToNotify) {
      listener.handleColorEvent(this.activeImageKey, filter);
    }

    emitDisplayImageEvent(this.activeImageKey);
  }

  private void emitFilterEvent(String filter) {
    for ( ViewListener listener : listenersToNotify) {
      listener.handleFilterEvent(this.activeImageKey, filter);
    }

    emitDisplayImageEvent(this.activeImageKey);
  }

  private void emitIntensityEvent() {
    for ( ViewListener listener : listenersToNotify) {
      listener.handleIntensityEvent(this.activeImageKey);
    }

    emitDisplayImageEvent(this.activeImageKey);
  }

  private void emitLumaEvent() {
    for ( ViewListener listener : listenersToNotify) {
      listener.handleLumaEvent(this.activeImageKey);
    }

    emitDisplayImageEvent(this.activeImageKey);
  }

  private void emitValueCompEvent() {
    for ( ViewListener listener : listenersToNotify) {
      listener.handleValueCompEvent(this.activeImageKey);
    }

    emitDisplayImageEvent(this.activeImageKey);
  }

  private void emitOneCompEvent(String color) {
    for ( ViewListener listener : listenersToNotify) {
      listener.handleOneCompEvent(this.activeImageKey, color);
    }

    emitDisplayImageEvent(this.activeImageKey);
  }

  private void emitSaveEvent() {
    String filePath = getFilePath(true);
    for ( ViewListener listener : listenersToNotify) {
      listener.handleSaveEvent(this.activeImageKey, filePath);
    }
  }

  /**
   * A helper method to enable passing messages from controller/exception catches.
   * @param text - the text to be displayed at the bottom right of the GUI
   */
  public void setDisplayText(String text) {
    this.displayText.setText(text);
  }

  private String getUserInput(String displayMsg) {
    String userInput = "";
    userInput = JOptionPane.showInputDialog(displayMsg);
    return userInput;
  }

  private String getFilePath(boolean saveMode) {
    JFileChooser fileChooser = new JFileChooser(); // initializes file chooser
    int response;
    if (saveMode) {
      response = fileChooser.showSaveDialog(null); // opens save dialogue/window
    }
    else {
      response = fileChooser.showOpenDialog(null); // opens load dialogue/window
    }
    if (response == JFileChooser.APPROVE_OPTION) { // if user selects a file
      return fileChooser.getSelectedFile().getAbsolutePath(); // returns absolute path
    }
    return "";
  }

  /**
   * A helper method to update my ImageList by creating new buttons and assigning action commands.
   * @param fileName - the name of the file as loaded/stored in the database
   */
  private void updateImageList(String fileName) {
    Objects.requireNonNull(fileName);
    // creates new button for opening/selecting images
    JButton selectImage = new JButton(fileName);
    imageList.add(selectImage); // adds button to list
    selectImage.setActionCommand(fileName); // sets command to filename with prefix Open:
    selectImage.addActionListener(this); // adds listener to button
  }

  /**
   * A big nasty switch statement to respond to action events from the GUI and call emitters.
   * @param e - the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String actionCommand = e.getActionCommand();
    if (actionCommand.startsWith("Open: ")) { // checks if user trying to open/display image
      emitDisplayImageEvent(actionCommand.substring(6)); // parses imageKey
      this.setDisplayText("Opened: " + this.activeImageKey); // sets display message
    }
    else {
      switch (actionCommand) {
        case "load": // load button pressed
          emitLoadEvent();
          break;
        case "save": // save button pressed
          emitSaveEvent();
          break;
        case "brighten": // brighten button pressed
          emitBrightenEvent();
          break;
        case "darken": // darken button pressed
          emitDarkenEvent();
          break;
        case "blur", "sharpen": // blur or sharpen button pressed
          emitFilterEvent(actionCommand); // emits filter event and passes proper filter
          break;
        case "sepia", "greyscale": // sepia or greyscale button pressed
          emitColorEvent(actionCommand); // emits color event and passes proper filter
          break;
        case "intensity": // intensity button pressed
          emitIntensityEvent();
          break;
        case "luma": // luma button pressed
          emitLumaEvent();
          break;
        case "valueComp": // value component button pressed
          emitValueCompEvent();
          break;
        case "red", "blue", "green": // one component button pressed
          emitOneCompEvent(actionCommand); // emits 1 component event and passes proper channel
          break;
        default: // not sure how this would even happen
          throw new IllegalArgumentException("Invalid command.");
      }
    }
  }
}
