Overview

- My command interface exists for a HashMap in my controller class. This enables me to store all
the commands in the HashMap and have them be an instance of Command. The Command interface has a
single method, execute() which is used to execute those commands. The key of the HashMap is the
command name, while the value is the execution of the Command with the appropriate parsed arguments.

- My ImageController interface exists in the case that I need to create a different controller for
later assignments. It has a getInput() method to fetch input from the readable, and a run() method
that acts as the main method for my controller (calling getInput(), parsing input, and running
appropriate commands.

- My ImageControllerImpl exists as the implementation to my ImageController interface. It contains
the methods mentioned above. It does not have any references to a particular ImageType or ImageIO
type. This should enable forward compatibility.

- My ImageIOFactory class exists so that my controller creates the correct type of ImageIO to read
the given file, based upon the file extension.

- My ImageIO interface exists so that I can create new concrete classes in the future to accommodate
other file types. It has read() and write() methods.

- My PPMImageIO class exists so that I can read() and write() PPMImage formats/types. Once we are
required to handle new ImageTypes, I can simply make a new ImageType concrete class if the methods
need to be different.

- My JpegPngImageIO class exists to enable the reading and writing of other image types. It is
an implementation of Java's ImageIO class.

- My ImageType interface exists so that I can create concrete classes for various image types.
It has several methods that should be implemented across Image Types, such as getPixel, setPixel,
getWidth, getHeight, and getMaxColorValue.

- My PPMImage class exists as an implementation of ImageType interface. It has those methods with
implementations relevant for a PPM image.

- My BufferedConverter class exists so that I can get and set pixel values that are stored in
bit strings, like those of JP(E)Gs and PNGs.

- My TransformationStrategy interface exists to hold the one method shared by all strategies,
applyTransformation(). This is overwritten with the relevant code in the concrete classes.

- My BrighteningStrategy class exists to implement the brightening method.

- My DarkeningStrategy class exists to implement the darkening method.

- My DeepFriedStrategy class exists to have some fun.

- My Intensity class exists to implement the intensity method.

- My LumaStrategy class exists to implement the luma transformation method.

- My OneComponentStrategy class exists to implement the one-component transformation method.

- My ValueComponentStrategy class exists to implement the value-component transformation method.

- My FilterStrategy class allows for applying kernel filtering (blur, sharpen).

- My ColorStrategy class allows for applying linear color transformations (sepia, greyscale). This
was implemented to achieve the sweet, sweet bonus points.

- My Pixel Interface exists in case we need to deal with various Pixel Types in the future.
This allows me to operate on Pixel objects in shared parts of my program, rather than hardcode
an RGBPixel that is used by PPMs.

- My RGBPixel class implements the Pixel Interface and holds the relevant methods for getting and
setting the channel components of an RGBPixel.

- My ImageDatabase class exists to enable the loading, transforming, and saving of multiple images
at once. I created a HashMap to store the ImageTypes under the given key 'nickname' provided by the
user.

- My EditorView Interface exists to enable me to implement various different view objects. I can
add concrete classes/implementations of this interface later to enable a GUI.

- My PhotoEditorView class exists to enable me to append to System.out, or an appendable object
like a StringBuilder. This is used for early stages of program development.

- My main class exists to act as the gateway to my program. It creates the necessary objects and
then calls controller.run() to begin listening and responding to input. It allows for a script
to be passed, and the controller will then read the commands from the script. If no script is given,
the controller will act on System.in as default.

Design
I have used the MVC design architecture and implemented Strategy Pattern for the various
transformation methods. I have abstracted all of my controller objects in order to allow for
various image formats down the line. I have also abstracted my view objects to enable for GUI
addition.

I added an ImageIO factory that will create the appropriate ImageIO class to handle
reading and writing of files.

I also added a JpegOrPngIO class to read and write JP(E)Gs and PNGs.

I added a BufferedConverter to convert the bit string pixels to my RGBPixel type.

*** Assignment 10 design additions ***

I added a GUI to my program. My GUI uses the Observer/Listener pattern.

This required adding several interfaces and classes including:
    - GUIControllerInterface - extends ViewListener so controller can be a listener for View-driven
    events/calls. It includes the methods the controller implements to handle those events.
    - GUIController - the implementation of the GUIControllerInterface class. It listens for calls
    from the view that call controller methods for handling those events.
    - ViewListener (interface) - this interface exists for controllers to implement, allowing the
    view to call handle() methods within the controller implementation.
    - GraphicalView - this class is the implementation of the GUI. It contains the swing and awt
    objects/classes necessary to build a functioning GUI. It establishes the action commands, and
    calls appropriate controller methods when those actions are detected.
    - ImageCanvas - this class exists and extends JPanel so that I may overwrite a few JPanel
    methods. I have used it to display my images and draw/display them in the center of the panel
    created to hold the canvas.

Changes (Assignment 9)
In order to add support for the filtering operation, I added a clone() method to my ImageTypes.
I also refactored each strategy to return an ImageType image that is the result of applying the
transformation strategy to the clone. This was necessary as the filter operations cannot modify the
original image, but rather the clone of the image. To support this filter operation, I also modified
the applyTransformation method for the images to return the ImageType. Furthermore, I modified
the applyTransformation method in the ImageDatabase to overwrite the HashMap containing the images
with the cloned, modified image. Hopefully, this will provide me with an easier avenue to provide
support for undoing image transformations. I will just need to add a second HashMap to store the
original images, apply the transformation, and then store the modified/cloned image in the other
HashMap. If a user undoes a transformation, I can overwrite the second HashMap with the original.
Users will addImage to both maps, and save from the second with the modified images. This will
ensure a user can addImage() and saveImage() and there will be no disconnect if modifications
have not been applied to any particular image.

I moved the ImageIO package to the controller to better adhere to the MVC architecture. This did not
require any other refactoring. I simply had it in the wrong place.

Changes (Assignment 10)
I only needed to change my main() method, as I had been looking for "-f" as the flag to use a script
while for assignment 10 it was specified to use "-file" instead. I also refactored the main()
to run the GUI if no arguments were provided.

Test photos
- lighthouse.ppm 200x133
- mokes.jpg 1778x1000
  - The blur method does indeed work on mokes.jpg, however due to the nature of the photo, it is not
    as prominent as it may be with other photos. Focus on the sunshine in order to see the effect.
- I took these photos and have authorized their use for these assignments.