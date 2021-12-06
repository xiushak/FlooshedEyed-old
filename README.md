# FlooshedEyed

A program to apply the fisheye effect on images. There is currently 3 versions of the effect.

![alt text](grid.jpg?raw=true)

The first version, EllipseFishEyeModel, maps the image onto an ellipse. This one does not allow you to change where the
fisheye effects centers at. An example of this version working on the grid image above is shown below.

![alt text](ellipseFisheyeGrid.png?raw=true)

The second version, FishEyeModel, allows you to move the center of the fisheyeEffect. Below is an example of the version

![alt text](FisheyeGrid.png?raw=true)

This one is an example where the effect was moved to the top left quadrant of the image

![alt text](movedFisheyeGrid.png?raw=true)

To use the FacialRecognitionFishEyeModel, you need to download OpenCV. I used opencv-453 in this implementation. Below
is an example using my face.

![alt_text](face.jpg?raw=true)
![alt text](faceFisheye.png?raw=true)

I also have a version that fisheyes on the brain to create the "big brain" effect.

![alt_text](faceFisheyeBrain.png?raw=true)
