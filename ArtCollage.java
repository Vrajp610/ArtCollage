/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage Flo2.jpeg
 *
 *  @author:
 *
 *************************************************************************/

import java.awt.Color;

public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename) {
        this.collageDimension = 4;
        this.tileDimension = 100;
        this.original = new Picture(filename);
        this.collage = new Picture(this.tileDimension * this.collageDimension, this.tileDimension * this.collageDimension);

        for(int tileC = 0; tileC < (4 * 100); tileC++){
            for(int tileR = 0; tileR < (4 * 100); tileR++){
                int originalC = tileC * original.width() / (4 * 100);
                int originalR = tileR * original.height() / (4 * 100);
                Color color = original.get(originalC, originalR);
                collage.set(tileC, tileR, color);
            }
        }

	    // WRITE YOUR CODE HERE
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename, int td, int cd) {
        this.collageDimension = cd;
        this.tileDimension = td;
        this.original = new Picture(filename);
        this.collage = new Picture(this.tileDimension * this.collageDimension, this.tileDimension * this.collageDimension);

        for(int tileC = 0; tileC < (this.tileDimension * this.collageDimension); tileC++){
            for(int tileR = 0; tileR < (this.tileDimension * this.collageDimension); tileR++){
                int originalC = tileC * original.width() / (this.tileDimension * this.collageDimension);
                int originalR = tileR * original.height() / (this.tileDimension * this.collageDimension);
                Color color = original.get(originalC, originalR);
                collage.set(tileC, tileR, color);
            }
        }

	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {
        return this.collageDimension;
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {
        return this.tileDimension;
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {
        return this.original;
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {
        return this.collage;
	    // WRITE YOUR CODE HERE
    }
    
    /*
     * Display the original image
     * Assumes that original has been initialized
     */
    public void showOriginalPicture() {
        this.original.show();
	    // WRITE YOUR CODE HERE
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() {
        this.collage.show();
	    // WRITE YOUR CODE HERE
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {
        Picture pictureFilename = new Picture(filename);

        for (int tileC = 0; tileC < this.tileDimension; tileC++){
            for (int tileR = 0; tileR < this.tileDimension; tileR++){
                int originalC = tileC * pictureFilename.width() / this.tileDimension;
                int originalR = tileR * pictureFilename.height() / this.tileDimension;
                Color color = pictureFilename.get(originalC, originalR);
                collage.set(tileC + (collageCol * this.tileDimension), tileR + (collageRow * this.tileDimension), color);
            }
        }
	    // WRITE YOUR CODE HERE
    }
    
    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */
    public void makeCollage () {
        for(int collageX = 0; collageX < this.collageDimension; collageX++){
            for(int collageY = 0; collageY < this.collageDimension; collageY++){
                for(int tileC = 0; tileC < this.tileDimension; tileC++){
                    for (int tileR = 0; tileR < this.tileDimension; tileR++){
                        int originalC = tileC * original.width() / this.tileDimension;
                        int originalR = tileR * original.height() / this.tileDimension;
                        Color color = original.get(originalC, originalR);
                        collage.set(tileC + (collageX * this.tileDimension), tileR + (collageY * this.tileDimension), color);
                     }
                }
            }
        }
	    // WRITE YOUR CODE HERE
    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see CS111 Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) {
		for (int tileC = (collageCol * this.tileDimension); tileC < this.tileDimension + (collageCol * this.tileDimension); tileC++){
			for (int tileR = (collageRow * this.tileDimension); tileR < this.tileDimension + (collageRow * this.tileDimension); tileR++){
				Color color = collage.get(tileC, tileR);
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				if (component.equals("red")){
					    collage.set(tileC, tileR, new Color(r, 0, 0));
				} else if (component.equals("green")) {
					    collage.set(tileC, tileR, new Color(0, g, 0));
				} else if (component.equals("blue")) {
					    collage.set(tileC, tileR, new Color(0, 0, b));
				}

			}
	    // WRITE YOUR CODE HERE
        }
    }

    /*
     * Grayscale tile at (collageCol, collageRow)
     * (see CS111 Week 9 slides, the code for luminance is at the book's website)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */

    public void grayscaleTile (int collageCol, int collageRow) {
        for (int tileC = collageCol * this.tileDimension; tileC < this.tileDimension + (collageCol * this.tileDimension); tileC++){
            for (int tileR = collageRow * this.tileDimension; tileR < this.tileDimension + (collageRow * this.tileDimension); tileR++){
                Color color = Luminance.toGray(collage.get(tileC, tileR));
                collage.set(tileC, tileR, color);
            }
        }
	    // WRITE YOUR CODE HERE
    }


    /*
     *
     *  Test client: use the examples given on the assignment description to test your ArtCollage
     */
    public static void main (String[] args) {
        ArtCollage art = new ArtCollage(args[0], 200, 2);
        art.makeCollage();

        // Replace 3 tiles 
        art.replaceTile(args[1],0,1);
        art.replaceTile(args[2],1,0);
        art.replaceTile(args[3],1,1);
        art.colorizeTile("green",0,0);
        art.showCollagePicture();
        //java ArtCollage Ariel.jpg Flo2.jpeg Flo.jpg Baloo.jpeg
    }
}
