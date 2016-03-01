package com.scotts.base;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
/*
import org.sikuli.script.Finder;
import org.sikuli.script.IScreen;
import org.sikuli.script.ImageLocator;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.SikuliEventObserver;*/

public class ArsinSikuliAPI extends ArsinFrameWork {/*
	public enum enumSikuli {
		above, below, getAutoWaitTimeout, getBottomLeft, getBottomRight, getCenter, getH, getW, getX, getY, getRect, getROI, getScreen, getLastMatch, getLastMatches, highlight, inside, keyUp, mouseUp, left, right, nearby, observe, listText, text, mouseDown, keyDown, setW, setH, setX, setY, click, doubleClick, drag, dropAt, exists, find, findAll, findAllNow, mouseMove, rightClick, wait, waitVanish, getEventManager, getFindFailedResponse, capture, getBounds, getGraphicsDevice, initBounds, getID, getNumberScreens, getPrimaryId, getRobot, selectRegion, toString, userCapture, dragAt, getLocationFromPSRML, getRegionFromPSRM, hover, getTopRight, getTopLeft, create, dragDrop, getThrowException, setRect, observeInBackground, setROI, stopObserver, updateSelf, type, setThrowException, toJythonRegion, setAutoWaitTimeout, paste, morphTo, newRegion, showClick, showDropTarget, showTarget, showMove, getPrimaryID, wheel, offset, targetOffset, similar, exact, getFilename, getImage, getTargetOffset, onAppear, onChange, onVanish, getScore, getTarget, equals, compareTo, addImagePath, getURL, locate, removeImagePath, searchFile, splitImagePath, getImagePath, remove, next, hasNext, findImageFile, clickAndWait
	}

	// ArsinFrameWork oAFW = new ArsinFrameWork();
	// ArsinSeleniumAPI oASFW= new ArsinSeleniumAPI();
	private enumSikuli enumCommand;

	private Screen screen;

	public ArsinSikuliAPI() {
		// TODO Auto-generated constructor stub
		screen = new Screen();

	}

	public enumSikuli checkEnumConstant(String sCommand) {
		enumCommand = null;
		try {
			enumCommand = enumSikuli.valueOf(sCommand);
		} catch (Exception e) {
			reportUnknownSikuliCmdErr(sCommand);
		}
		return enumCommand;
	}

	public Object effecta(Finder FinObj, String command) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case remove:
				FinObj.remove();
				reportStepDetails("Removing finder containing data", "Successfully removed finder containing data",
						PASS);
				break;
			case next:
				returnObj = FinObj.next();
				reportStepDetails("Getting image present in the finder", "Successfully get image present in the finder",
						PASS);
				break;
			case hasNext:
				returnObj = FinObj.hasNext();
				reportStepDetails("Checking image present in the finder",
						"Successfully checked image present in the finder", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Finder FinObj, String command, String ptn) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(ptn);
			switch (enumCommand) {

			case find:
				FinObj.find(SourceImagePath);
				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;
			case findAll:
				FinObj.findAll(SourceImagePath);

				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				returnObj = FinObj;

				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}

		return returnObj;
	}

	public Object effecta(Finder FinObj, String command, String templateFilename, double minSimilarity) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(templateFilename);
			switch (enumCommand) {

			case find:
				FinObj.find(SourceImagePath, minSimilarity);
				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;
			case findAll:
				FinObj.findAll(SourceImagePath, minSimilarity);
				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Finder FinObj, String command, String templateFilename, double minSimilarity,
			String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(templateFilename);
			switch (enumCommand) {

			case find:
				FinObj.find(SourceImagePath, minSimilarity);
				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;
			case findAll:
				FinObj.findAll(SourceImagePath, minSimilarity);
				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Finder FinObj, String command, String ptn, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case find:
				FinObj.find(ptn);
				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;
			case findAll:
				FinObj.findAll(ptn);
				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(ImageLocator ImagLoc, String command) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);

			switch (enumCommand) {

			case getImagePath:
				returnObj = ImageLocator.getImagePath();
				reportStepDetails("Getting Image path", "Successfully get image path", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(ImageLocator ImagLoc, String command, String Value) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(Value);
			switch (enumCommand) {

			case addImagePath:
				ImageLocator.addImagePath(Value);
				reportStepDetails("Adding given path to list image search path",
						"Successfully added path to list image search path", PASS);
				break;
			case getURL:
				// returnObj=ImagLoc.getURL(Value);
				reportStepDetails("Comparing two matched objects are equal ",
						"Successfully compared two matched objects are equal", PASS);
				break;
			case locate:
				returnObj = ImagLoc.locate(SourceImagePath);
				System.out.println("Report Enter");
				reportStepDetails("Locating given Image ", "Successfully located given image", PASS);
				System.out.println("Report Enter");
				break;

			case removeImagePath:
				ImageLocator.removeImagePath(Value);
				reportStepDetails("Removing given image path from ImageLocator Object",
						"Successfully removed image path from imageLocator object", PASS);
				break;
			case searchFile:
				// ImagLoc.searchFile(Value);

			case splitImagePath:
				// ImagLoc.splitImagePath(Value);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(ImageLocator ImagLoc, String command, String Value, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(Value);
			switch (enumCommand) {

			case addImagePath:
				ImageLocator.addImagePath(Value);
				reportStepDetails("Adding given path to list image search path",
						"Successfully added path to list image search path", PASS);
				break;
			case getURL:
				// returnObj=ImagLoc.getURL(Value);
				reportStepDetails("Comparing two matched objects are equal ",
						"Successfully compared two matched objects are equal", PASS);
				break;
			case locate:

				returnObj = ImagLoc.locate(SourceImagePath);
				reportStepDetails("Locating given Image: " + FieldName,
						"Successfully located given " + FieldName + "image", PASS);
				break;
			case removeImagePath:
				ImageLocator.removeImagePath(Value);
				reportStepDetails("Removing given image path from ImageLocator Object",
						"Successfully removed image path from imageLocator object", PASS);
				break;
			case searchFile:
				// ImagLoc.searchFile(Value);

			case splitImagePath:
				// ImagLoc.splitImagePath(Value);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Match m, String command) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case getScore:
				returnObj = m.getScore();
				reportStepDetails("Getting not matched score of referenced Image",
						"Successfully get not matched score of the referenced Image", PASS);
				break;
			case getTarget:
				returnObj = m.getTarget();
				reportStepDetails("Getting the location of the region", "Successfully get location of region", PASS);
				break;
			case text:
				returnObj = m.text();
				reportStepDetails("Getting complete text present in the referenced image",
						"Successfully get complete text present in the  referenced image", PASS);
				break;

			case toString:
				returnObj = m.toString();
				reportStepDetails("Converting given object type into string object",
						"Successfully converted object into string object", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Match m, String command, java.lang.Object Obj) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case compareTo:
				returnObj = m.compareTo(Obj);
				reportStepDetails("Comparing source and target Images are same ",
						"Successfully compared source and target Images are same", PASS);
				break;
			case equals:
				returnObj = m.equals(Obj);
				reportStepDetails("Comparing two matched objects are equal ",
						"Successfully compared two matched objects are equal", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Match m, String command, java.lang.Object Obj, String FiledName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case compareTo:
				returnObj = m.compareTo(Obj);
				reportStepDetails("Comparing source and target Images are same ",
						"Successfully compared source and target Images are same", PASS);
				break;
			case equals:
				returnObj = m.equals(Obj);
				reportStepDetails("Comparing two matched objects are equal ",
						"Successfully compared two matched objects are equal", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Match m, String command, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case getScore:
				returnObj = m.getScore();
				reportStepDetails("Getting not matched score of referenced Image",
						"Successfully get not matched score of the referenced Image", PASS);
				break;
			case getTarget:
				returnObj = m.getTarget();
				reportStepDetails("Getting the location of the region", "Successfully get location of region", PASS);
				break;
			case text:
				returnObj = m.text();
				reportStepDetails("Getting complete text present in the referenced image",
						"Successfully get complete text present in the  referenced image", PASS);
				break;

			case toString:
				returnObj = m.toString();
				reportStepDetails("Converting given object type into string object",
						"Successfully converted object into string object", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Object obj, String command, boolean flag) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, flag);
		else if (obj instanceof Pattern)
			return effecta(obj, command, flag);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, flag);
		else if (obj instanceof Finder)
			return effecta(obj, command, flag);
		return null;
	}

	public Object effecta(Object obj, String command, boolean flag, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, flag, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, flag, FieldName);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, flag, FieldName);
		else if (obj instanceof Finder)
			return effecta(obj, command, flag, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, double secs) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, secs);
		else if (obj instanceof Pattern)
			return effecta(obj, command, secs);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, secs);
		else if (obj instanceof Finder)
			return effecta(obj, command, secs);
		return null;
	}

	public Object effecta(Object obj, String command, double secs, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, secs, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, secs, FieldName);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, secs, FieldName);
		else if (obj instanceof Finder)
			return effecta(obj, command, secs, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, float secs) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, secs);
		else if (obj instanceof Pattern)
			return effecta((Pattern) obj, command, secs);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, secs);
		else if (obj instanceof Finder)
			return effecta(obj, command, secs);
		return null;
	}

	public Object effecta(Object obj, String command, float secs, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, secs, FieldName);
		else if (obj instanceof Pattern)
			return effecta((Pattern) obj, command, secs, FieldName);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, secs, FieldName);
		else if (obj instanceof Finder)
			return effecta(obj, command, secs, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, int value) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, value);
		
		 * else if(obj instanceof Pattern) return
		 * effecta((Pattern)obj,command,value);
		 
		else if (obj instanceof Match)
			return effecta((Match) obj, command, value);
		else if (obj instanceof Finder)
			return effecta(obj, command, value);
		return null;
	}

	public Object effecta(Object obj, String command, int direction, int steps) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, direction, steps);
		else if (obj instanceof Pattern)
			return effecta((Pattern) obj, command, direction, steps);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, direction, steps);
		else if (obj instanceof Finder)
			return effecta(obj, command, direction, steps);
		return null;
	}

	public Object effecta(Object obj, String command, int xaxis, int yaxis, int width, int height) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, xaxis, yaxis, width, height);
		else if (obj instanceof Pattern)
			return effecta(obj, command, xaxis, yaxis, width, height);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, xaxis, yaxis, width, height);
		else if (obj instanceof Finder)
			return effecta(obj, command, xaxis, yaxis, width, height);
		return null;
	}

	public Object effecta(Object obj, String command, int direction, int steps, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, direction, steps, FieldName);
		else if (obj instanceof Pattern)
			return effecta((Pattern) obj, command, direction, steps, FieldName);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, direction, steps, FieldName);
		else if (obj instanceof Finder)
			return effecta(obj, command, direction, steps, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, int threshold, SikuliEventObserver observer) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, threshold, observer);
		else if (obj instanceof Pattern)
			return effecta(obj, command, threshold, observer);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, threshold, observer);
		else if (obj instanceof Finder)
			return effecta(obj, command, threshold, observer);
		return null;
	}

	public Object effecta(Object obj, String command, int threshold, SikuliEventObserver observer, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, threshold, observer, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, threshold, observer, FieldName);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, threshold, observer, FieldName);
		else if (obj instanceof Finder)
			return effecta(obj, command, threshold, observer, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, int value, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, value, FieldName);
		
		 * else if(obj instanceof Pattern) return
		 * effecta((Pattern)obj,command,value,FieldName);
		 
		else if (obj instanceof Match)
			return effecta((Match) obj, command, value, FieldName);
		else if (obj instanceof Finder)
			return effecta(obj, command, value, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, java.awt.Rectangle rect) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, rect);
		else if (obj instanceof Pattern)
			return effecta(obj, command, rect);
		
		 * else if(obj instanceof Match) return
		 * effecta((Match)obj,command,rect);
		 
		else if (obj instanceof Finder)
			return effecta(obj, command, rect);
		return null;
	}

	public Object effecta(Object obj, String command, java.awt.Rectangle r, IScreen parentScreen) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, r, parentScreen);
		else if (obj instanceof Pattern)
			return effecta(obj, command, r, parentScreen);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, r, parentScreen);
		else if (obj instanceof Finder)
			return effecta(obj, command, r, parentScreen);
		return null;
	}

	public Object effecta(Object obj, String command, java.awt.Rectangle r, IScreen parentScreen, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, r, parentScreen, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, r, parentScreen, FieldName);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, r, parentScreen, FieldName);
		else if (obj instanceof Finder)
			return effecta(obj, command, r, parentScreen, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, java.awt.Rectangle rect, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, rect, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, rect, FieldName);
		
		 * else if(obj instanceof Match) return
		 * effecta((Match)obj,command,rect,FieldName);
		 
		else if (obj instanceof Finder)
			return effecta(obj, command, rect, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, java.lang.Object Obj1) {
		if (obj instanceof Match)
			return effecta((Match) obj, command, Obj1);
		return null;
	}

	public Object effecta(Object obj, String command, java.lang.Object Obj, String FiledName) {
		if (obj instanceof Match)
			return effecta((Match) obj, command, Obj, FiledName);
		return null;
	}

	public Object effecta(Object obj, String command, Location Loc) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, Loc);
		else if (obj instanceof Pattern)
			return effecta(obj, command, Loc);
		
		 * else if(obj instanceof Match) return effecta((Match)obj,command,Loc);
		 
		else if (obj instanceof Finder)
			return effecta(obj, command, Loc);
		return null;
	}

	public Object effecta(Object obj, String command, Location Loc, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, Loc, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, Loc, FieldName);
		
		 * else if(obj instanceof Match) return
		 * effecta((Match)obj,command,Loc,FieldName);
		 
		else if (obj instanceof Finder)
			return effecta(obj, command, Loc, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, Region r) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, r);
		else if (obj instanceof Pattern)
			return effecta(obj, command, r);
		
		 * else if(obj instanceof Match) return effecta((Match)obj,command,r);
		 
		else if (obj instanceof Finder)
			return effecta(obj, command, r);
		return null;
	}

	public Object effecta(Object obj, String command, Region r, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, r, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, r, FieldName);
		
		 * else if(obj instanceof Match) return
		 * effecta((Match)obj,command,r,FieldName);
		 
		else if (obj instanceof Finder)
			return effecta(obj, command, r, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, SikuliEventObserver observer) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, observer);
		else if (obj instanceof Pattern)
			return effecta(obj, command, observer);
		
		 * else if(obj instanceof Match) return
		 * effecta((Match)obj,command,observer);
		 
		else if (obj instanceof Finder)
			return effecta(obj, command, observer);
		return null;
	}

	public Object effecta(Object obj, String command, SikuliEventObserver observer, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, observer, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, observer, FieldName);
		
		 * else if(obj instanceof Match) return
		 * effecta((Match)obj,command,observer,FieldName);
		 
		else if (obj instanceof Finder)
			return effecta(obj, command, observer, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, FieldName);
		else if (obj instanceof Pattern)
			return effecta((Pattern) obj, command, FieldName);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, FieldName);
		else if (obj instanceof Finder)
			return effecta((Finder) obj, command, FieldName);
		else if (obj instanceof ImageLocator)
			return effecta((ImageLocator) obj, command, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, String target, double delaytime) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, target, delaytime);
		else if (obj instanceof Pattern)
			return effecta(obj, command, target, delaytime);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, target, delaytime);
		else if (obj instanceof Finder)
			return effecta((Finder) obj, command, target, delaytime);
		return null;
	}

	// public Object effecta(Object obj, String command, String target,
	// double delaytime, String FieldName) {
	// if (obj instanceof Region)
	// return effecta((Region) obj, command, target, delaytime, FieldName);
	// else if (obj instanceof Pattern)
	// return effecta(obj, command, target, delaytime, FieldName);
	// else if (obj instanceof Match)
	// return effecta((Match) obj, command, target, delaytime, FieldName);
	// else if (obj instanceof Finder)
	// return effecta((Finder) obj, command, target, delaytime, FieldName);
	// return null;
	// }

	public Object effecta(Object obj, String command, String target, int modifier, double similarityValue) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, target, modifier, similarityValue);
		else if (obj instanceof Pattern)
			return effecta(obj, command, target, modifier);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, target, modifier);
		
		 * else if(obj instanceof Finder) return
		 * effecta((Finder)obj,command,target,modifier);
		 
		return null;
	}

	public Object effecta(Object obj, String command, String target, int direction, int steps, double similarityValue) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, target, direction, steps, similarityValue);
		else if (obj instanceof Pattern)
			return effecta(obj, command, target, direction, steps, similarityValue);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, target, direction, steps);
		else if (obj instanceof Finder)
			return effecta(obj, command, target, direction, steps, similarityValue);
		return null;
	}

	public Object effecta(Object obj, String command, String target, int direction, int steps, String FieldName,
			double similarityValue) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, target, direction, steps, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, target, direction, steps, FieldName, similarityValue);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, target, direction, steps, FieldName);
		else if (obj instanceof Finder)
			return effecta(obj, command, target, direction, steps, FieldName, similarityValue);
		return null;
	}

	public Object effecta(Object obj, String command, String target, double modifier, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, target, modifier, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, target, modifier, FieldName);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, target, modifier, FieldName);
		
		 * else if(obj instanceof Finder) return
		 * effecta((Finder)obj,command,target,modifier,FieldName);
		 
		return null;
	}

	public Object effecta(Object obj, String command, String PSCtarget, SikuliEventObserver observer) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, PSCtarget, observer);
		else if (obj instanceof Pattern)
			return effecta(obj, command, PSCtarget, observer);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, PSCtarget, observer);
		else if (obj instanceof Finder)
			return effecta(obj, command, PSCtarget, observer);
		return null;
	}

	public Object effecta(Object obj, String command, String PSCtarget, SikuliEventObserver observer,
			String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, PSCtarget, observer, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, PSCtarget, observer, FieldName);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, PSCtarget, observer, FieldName);
		else if (obj instanceof Finder)
			return effecta(obj, command, PSCtarget, observer, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, String sourcetarget, String obj1) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, sourcetarget, obj1);
		else if (obj instanceof Pattern)
			return effecta(obj, command, sourcetarget, obj1);
		
		 * else if(obj instanceof Match) return
		 * effecta((Match)obj,command,sourcetarget,obj1);
		 
		else if (obj instanceof Finder)
			return effecta((Finder) obj, command, sourcetarget, obj1);
		else if (obj instanceof ImageLocator)
			return effecta((ImageLocator) obj, command, sourcetarget, obj1);
		return null;
	}

	public Object effecta(Object obj, String command, String sourcetarget, String obj1, int modifiers,
			double similarityValue) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, sourcetarget, obj1, modifiers);
		else if (obj instanceof Pattern)
			return effecta(obj, command, sourcetarget, obj1, modifiers, similarityValue);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, sourcetarget, obj1, modifiers);
		else if (obj instanceof Finder)
			return effecta(obj, command, sourcetarget, obj1, modifiers, similarityValue);
		return null;
	}

	public Object effecta(Object obj, String command, String sourcetarget, String obj1, int modifiers,
			String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, sourcetarget, obj1, modifiers, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, sourcetarget, obj1, modifiers, FieldName);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, sourcetarget, obj1, modifiers, FieldName);
		else if (obj instanceof Finder)
			return effecta(obj, command, sourcetarget, obj1, modifiers, FieldName);
		return null;
	}

	public Object effecta(Object obj, String command, String sourcetarget, String obj1, String FieldName) {
		if (obj instanceof Region)
			return effecta((Region) obj, command, sourcetarget, obj1, FieldName);
		else if (obj instanceof Pattern)
			return effecta(obj, command, sourcetarget, obj1, FieldName);
		else if (obj instanceof Match)
			return effecta((Match) obj, command, sourcetarget, obj1, FieldName);
		else if (obj instanceof Finder)
			return effecta(obj, command, sourcetarget, obj1, FieldName);
		return null;
	}

	public Object effecta(Pattern p, String command) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case exact:
				returnObj = p.exact();
				reportStepDetails("Comparing both image are exactly equals",
						"Successfully compared both images are same", PASS);
				break;
			case getFilename:
				returnObj = p.getFilename();
				reportStepDetails("Getting  filename of referenced Image",
						"Successfully get filename of referenced image", PASS);
				break;
			case getImage:
				returnObj = p.getImage();
				reportStepDetails("Getting referenced Image", "Successfully get referenced image", PASS);
				break;
			case getTargetOffset:
				returnObj = p.getTargetOffset();
				reportStepDetails("Getting  the target offset value of referenced Image",
						"Successfully get target offset value of referenced image", PASS);
				break;
			case toString:
				returnObj = p.toString();
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Pattern p, String command, float similarity) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case similar:
				returnObj = p.similar(similarity);
				reportStepDetails("Matching the similarities of Image", "Successfully Matched the similarity of image",
						PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Pattern p, String command, float similarity, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case similar:
				returnObj = p.similar(similarity);
				reportStepDetails("Matching the similarities of Image:" + FieldName,
						"Successfully Matched the similarity of image: " + FieldName, PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Pattern p, String command, int dxaxis, int dyaxis) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case targetOffset:
				returnObj = p.targetOffset(dxaxis, dyaxis);
				reportStepDetails("Setting target offset values to referenced image",
						"Successfully set offset values to referenced image", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Pattern p, String command, int dxaxis, int dyaxis, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case targetOffset:
				returnObj = p.targetOffset(dxaxis, dyaxis);
				reportStepDetails("Setting target offset values to referenced image: " + FieldName,
						"Successfully set offset values to referenced image: " + FieldName, PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Pattern p, String command, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case exact:
				returnObj = p.exact();
				reportStepDetails("Comparing both image are exactly equals: " + FieldName,
						"Successfully compared both images are same", PASS);
				break;
			case getFilename:
				returnObj = p.getFilename();
				reportStepDetails("Getting  filename of referenced Image: " + FieldName,
						"Successfully get filename of " + FieldName + "  referenced image", PASS);
				break;
			case getImage:
				returnObj = p.getImage();
				reportStepDetails("Getting referenced Image: " + FieldName,
						"Successfully get " + FieldName + "  referenced image", PASS);
				break;
			case getTargetOffset:
				returnObj = p.getTargetOffset();
				reportStepDetails("Getting  the target offset value of referenced Image: " + FieldName,
						"Successfully get target offset value of " + FieldName + " referenced image", PASS);
				break;
			case toString:
				returnObj = p.toString();
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	// ****************Start Effecta Commands************************
	public Object effecta(Region reg, String command) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case below:
				returnObj = reg.below();
				reportStepDetails("Getting  below coordinates of referenced image",
						"Successfully get below coordinates of referenced image", PASS);
				break;

			case above:
				returnObj = reg.above();
				reportStepDetails("Getting above coordinates of referenced image",
						"Successfully get above coordinates of referenced image", PASS);
				break;

			case getAutoWaitTimeout:
				returnObj = reg.getAutoWaitTimeout();
				reportStepDetails("Getting auto wait timeout ", "Successfully get auto wait timeout", PASS);
				break;

			case getBottomLeft:
				returnObj = reg.getBottomLeft();
				reportStepDetails("Getting bottom left corner coordinates of referenced image",
						"Successfully get bottom corner left coordinates of referenced image", PASS);
				break;

			case getBottomRight:
				returnObj = reg.getBottomRight();
				reportStepDetails("Getting bottom right corner coordinates of referenced image",
						"Successfully get bottom right corner coordinates of referenced image", PASS);
				break;

			case getCenter:
				returnObj = reg.getCenter();
				reportStepDetails("Getting  center corner coordinates of referenced image",
						"Successfully get center corner coordinates of referenced image", PASS);
				break;

			case getFindFailedResponse:
				returnObj = reg.getFindFailedResponse();
				reportStepDetails("Getting type of execution", "Successfully get type of execution", PASS);
				break;

			case getW:
				returnObj = reg.getW();
				reportStepDetails("Getting width of referenced image", "Successfully get width of referenced image",
						PASS);
				break;

			case getH:
				returnObj = reg.getH();
				reportStepDetails("Getting height of referenced image", "Successfully get height of referenced image",
						PASS);
				break;

			case getX:
				returnObj = reg.getX();
				reportStepDetails("Getting x-axis of referenced image", "Successfully get x-axis of referenced image",
						PASS);
				break;

			case getY:
				returnObj = reg.getY();
				reportStepDetails("Getting y-axis of referenced image", "Successfully get y-axis of referenced image",
						PASS);
				break;

			case getRect:
				returnObj = reg.getRect();
				reportStepDetails("Getting rectangle of referenced image",
						"Successfully get rectangle of referenced image", PASS);
				break;
			case getROI:
				returnObj = reg.getROI();
				reportStepDetails("Getting rectangle of referenced image",
						"Successfully get rectangle of referenced image", PASS);
				break;

			case getScreen:
				returnObj = reg.getScreen();
				reportStepDetails("Getting screen of referenced image", "Successfully get screen of referenced image",
						PASS);
				break;

			case getLastMatch:
				returnObj = reg.getLastMatch();
				reportStepDetails("Getting last match of referenced image",
						"Successfully get last matched Image of referenced image", PASS);
				break;
			case getLastMatches:
				returnObj = reg.getLastMatches();
				reportStepDetails("Getting last matchesof referenced image",
						"Successfully get last matches Image of referenced image", PASS);
				break;
			case highlight:
				reg.highlight();
				reportStepDetails("Highlighting referenced image", "Successfully highlighted referenced image", PASS);
				break;

			case inside:
				returnObj = reg.inside();
				reportStepDetails("Checking smaller portion of image similarity",
						"Successfully checked smaller portion of image similaity", PASS);
				break;

			case keyUp:
				reg.keyUp();
				reportStepDetails("Releasing keys specified in value", "Successfully released keys specified in value",
						PASS);
				break;

			case mouseUp:
				reg.mouseUp();
				reportStepDetails("Releasing mouse button specified by value",
						"Successfully release mouse button specified by value", PASS);
				break;

			case left:
				returnObj = reg.left();
				reportStepDetails("Getting left coordinates of referenced image",
						"Successfully get left coordinates of referenced image", PASS);
				break;

			case right:
				returnObj = reg.right();
				reportStepDetails("Getting right coordinates of referenced image",
						"Successfully get right coordinates of referenced image", PASS);
				break;
			case nearby:
				returnObj = reg.nearby();
				reportStepDetails("Getting nearby region of referenced image",
						"Successfully nearby region of referenced image", PASS);
				break;

			case observe:
				reg.observe();

			case listText:
				// returnObj=reg.listText();

			case text:
				returnObj = reg.text();
				reportStepDetails("Getting text present in the referenced image",
						"Successfully get text present in the  referenced image", PASS);
				break;

			case getTopLeft:
				returnObj = reg.getTopLeft();
				reportStepDetails("Getting top left coordinates of referenced image",
						"Successfully get top left coordinates of referenced image", PASS);
				break;

			case getTopRight:
				returnObj = reg.getTopRight();
				reportStepDetails("Getting top right coordinates of referenced image",
						"Successfully get top right coordinates of referenced image", PASS);
				break;

			case getThrowException:
				returnObj = reg.getThrowException();
				reportStepDetails("Getting throw exception status", "Successfully get throw exception status", PASS);
				break;

			case stopObserver:
				reg.stopObserver();
				reportStepDetails("Stopping observer", "Successfully stop observer", PASS);
				break;

			case toString:
				returnObj = reg.toString();

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, boolean flag) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case setThrowException:
				reg.setThrowException(flag);

			case highlight:
				// reg.highlight(flag);
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, boolean flag, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case setThrowException:
				reg.setThrowException(flag);

			case highlight:
				// reg.highlight(flag);
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, double secs) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case observe:
				reg.observe(secs);
			case observeInBackground:
				reg.observeInBackground(secs);
			case setAutoWaitTimeout:
				reg.setAutoWaitTimeout(secs);
				reportStepDetails("Increasing the auto wait time as specified target ",
						"Successfully  Increased " + secs + " the auto wait time as specified target", PASS);
				break;
			case wait:
				reg.wait(secs);
				reportStepDetails("Checking process waits until Button/Image of field appears",
						"Successfully waits process " + secs + " secs  until Button/Image of field appears", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, double secs, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case observe:
				reg.observe(secs);
			case observeInBackground:
				reg.observeInBackground(secs);
			case setAutoWaitTimeout:
				reg.setAutoWaitTimeout(secs);
				reportStepDetails("Increasing the auto wait time as specified target ",
						"Successfully  Increased " + secs + " the auto wait time as specified target", PASS);
				break;
			case wait:
				reg.wait(secs);
				reportStepDetails("Checking process waits until Button/Image of field appears",
						"Successfully waits process " + secs + " secs  until Button/Image of field appears", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, float secs) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case highlight:
				reg.highlight(secs);
				reportStepDetails("Highlighting referenced image", "Successfully highlighted referenced image", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, float secs, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case highlight:
				reg.highlight(secs);
				reportStepDetails("Highlighting referenced image" + FieldName,
						"Successfully highlighted " + FieldName + " referenced image", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, int value) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);

			switch (enumCommand) {

			case above:
				returnObj = reg.above(value);
				reportStepDetails("Getting above coordinates of referenced image",
						"Successfully get above coordinates of referenced image", PASS);
				break;

			case below:
				returnObj = reg.below(value);
				reportStepDetails("Getting below coordinates of referenced image",
						"Successfully get below coordinates of referenced image", PASS);
				break;
			case left:
				returnObj = reg.left(value);
				reportStepDetails("Getting left coordinates of referenced image",
						"Successfully get left coordinates of referenced image", PASS);
				break;
			case keyDown:
				reg.keyDown(value);
				reportStepDetails("Pressing keydown specified in value",
						"Successfully press down the key specified in value", PASS);
				break;
			case keyUp:
				reg.keyUp(value);
				reportStepDetails("Releasing keys specified in value", "Successfully released keys specified in value",
						PASS);
				break;
			case mouseDown:
				reg.mouseDown(value);
				reportStepDetails("Clicking mouse button specified by value",
						"Successfully click mouse button specified by value", PASS);
				break;
			case mouseUp:
				reg.mouseUp(value);
				reportStepDetails("Releasing mouse button specified by value",
						"Successfully release mouse button specified by value", PASS);
				break;
			case right:
				reg.right(value);
				reportStepDetails("Getting right coordinates of referenced image",
						"Successfully get right coordinates of referenced image", PASS);
				break;
			case setH:
				reg.setH(value);
				reportStepDetails("Setting Image height as specified by value",
						"Successfully Set Image height as specified by value", PASS);
				break;
			case setW:
				reg.setW(value);
				reportStepDetails("Setting Image width as specified by value",
						"Successfully Set Image width as specified by value", PASS);
				break;
			case setX:
				reg.setX(value);
				reportStepDetails("Setting Image left size as specified by value",
						"Successfully Set left size as specified by value", PASS);
				break;
			case setY:
				reg.setY(value);
				reportStepDetails("Setting Image right size as specified by value",
						"Successfully Set right size as specified by value", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, int direction, int steps) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case wheel:
				returnObj = reg.wheel(direction, steps);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, int xaxis, int yaxis, int width, int height) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Region.create(xaxis, yaxis, width, height);
				reportStepDetails("Creating region with specified coordinates",
						"Successfully create region with specified coordinates", PASS);
				break;
			case setRect:
				reg.setRect(xaxis, yaxis, width, height);
				reportStepDetails("Creating rectangle with specified coordinates",
						"Successfully create rectangle with specified coordinates", PASS);
				break;
			case setROI:
				reg.setROI(xaxis, yaxis, width, height);
				reportStepDetails("Creating rectangle with specified coordinates",
						"Successfully create rectangle with specified coordinates", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	// doubt
	public Object effecta(Region reg, String command, int xaxis, int yaxis, int width, int height, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Region.create(xaxis, yaxis, width, height);
				reportStepDetails("Creating region with specified coordinates" + FieldName,
						"Successfully create region " + FieldName + " with specified coordinates", PASS);
				break;
			case setRect:
				reg.setRect(xaxis, yaxis, width, height);
				reportStepDetails("Creating rectangle with specified coordinates" + FieldName,
						"Successfully create rectangle " + FieldName + " with specified coordinates", PASS);
				break;
			case setROI:
				reg.setROI(xaxis, yaxis, width, height);
				reportStepDetails("Creating rectangle with specified coordinates: " + FieldName,
						"Successfully create rectangle " + FieldName + " with specified coordinates", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, int direction, int steps, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case wheel:
				returnObj = reg.wheel(direction, steps);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, int threshold, SikuliEventObserver observer) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case onChange:

				reg.onChange(threshold, observer);
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, int threshold, SikuliEventObserver observer, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case onChange:

				reg.onChange(threshold, observer);
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, int value, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case above:
				returnObj = reg.above(value);
				reportStepDetails("Getting above coordinates of referenced image" + FieldName,
						"Successfully get above coordinates of " + FieldName + " referenced image", PASS);
				break;

			case below:
				returnObj = reg.below(value);
				reportStepDetails("Getting below coordinates of referenced image: " + FieldName,
						"Successfully get below coordinates of " + FieldName + " referenced image", PASS);
				break;
			case left:
				returnObj = reg.left(value);
				reportStepDetails("Getting left coordinates of referenced image: " + FieldName,
						"Successfully get left coordinates of " + FieldName + " referenced image", PASS);
				break;
			case keyDown:
				reg.keyDown(value);
				reportStepDetails("Pressing keydown specified in value",
						"Successfully press down the key specified in value", PASS);
				break;
			case keyUp:
				reg.keyUp(value);
				reportStepDetails("Releasing keys specified in value", "Successfully released keys specified in value",
						PASS);
				break;
			case mouseDown:
				reg.mouseDown(value);
				reportStepDetails("Clicking mouse button specified by value",
						"Successfully click mouse button specified by value", PASS);
				break;
			case mouseUp:
				reg.mouseUp(value);
				reportStepDetails("Releasing mouse button specified by value",
						"Successfully release mouse button specified by value", PASS);
				break;
			case right:
				reg.right(value);
				reportStepDetails("Getting right coordinates of referenced image: " + FieldName,
						"Successfully get right coordinates of " + FieldName + "referenced image", PASS);
				break;
			case setH:
				reg.setH(value);
				reportStepDetails("Setting Image height as specified by value: " + FieldName,
						"Successfully Set Image height of " + FieldName + " as specified by value", PASS);
				break;
			case setW:
				reg.setW(value);
				reportStepDetails("Setting Image width as specified by value: " + FieldName,
						"Successfully Set Image width of " + FieldName + " as specified by value", PASS);
				break;
			case setX:
				reg.setX(value);
				reportStepDetails("Setting Image left size as specified by value: " + FieldName,
						"Successfully Set left size of " + FieldName + " as specified by value", PASS);
				break;
			case setY:
				reg.setY(value);
				reportStepDetails("Setting Image right size as specified by value: " + FieldName,
						"Successfully Set right size of " + FieldName + " as specified by value", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, java.awt.Rectangle rect) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Region.create(rect);
				reportStepDetails("Creating region with specified rectangle",
						"Successfully create region with specified rectangle", PASS);
				break;
			case setROI:
				reg.setROI(rect);
				reportStepDetails("Setting rectangle with specified rectangle",
						"Successfully create region with specified rectangle", PASS);
				break;
			case setRect:
				reg.setRect(rect);
				reportStepDetails("Setting rectangle with specified rectangle",
						"Successfully create region with specified rectangle", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, java.awt.Rectangle r, IScreen parentScreen) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Region.create(r, parentScreen);
				reportStepDetails("Creating region with specified coordinates",
						"Successfully create region with specified coordinates", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, java.awt.Rectangle r, IScreen parentScreen, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Region.create(r, parentScreen);
				reportStepDetails("Creating region with specified coordinates: " + FieldName,
						"Successfully create region with specified coordinates", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, java.awt.Rectangle rect, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Region.create(rect);
				reportStepDetails("Creating region with specified rectangle: " + FieldName,
						"Successfully create region " + FieldName + " with specified rectangle", PASS);
				break;
			case setROI:
				reg.setROI(rect);
				reportStepDetails("Setting rectangle with specified rectangle: " + FieldName,
						"Successfully create region " + FieldName + "  with specified rectangle", PASS);
				break;
			case setRect:
				reg.setRect(rect);
				reportStepDetails("Setting rectangle with specified rectangle: " + FieldName,
						"Successfully create region " + FieldName + "  with specified rectangle", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, Location Loc) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case offset:
				returnObj = reg.offset(Loc);
				reportStepDetails("Getting offset of location", "Successfully get offset of location", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, Location Loc, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case offset:
				returnObj = reg.offset(Loc);
				reportStepDetails("Getting offset of location: " + FieldName,
						"Successfully get offset of " + FieldName + " location", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, Region r) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Region.create(r);
				reportStepDetails("Creating region with specified region",
						"Successfully created region with specified region", PASS);
				break;
			case setRect:
				reg.setRect(r);
				reportStepDetails("Setting rectangle with specified region",
						"Successfully set rectangle with specified region", PASS);
				break;
			case setROI:
				reg.setROI(r);
				reportStepDetails("Setting rectangle with specified region",
						"Successfully set rectangle with specified region", PASS);
				break;
			case toJythonRegion:
				returnObj = Region.toJythonRegion(r);
				reportStepDetails("Creating a duplicate region as specified region",
						"Successfully created region as specified region", PASS);
				break;
			case morphTo:
				returnObj = reg.morphTo(r);
				reportStepDetails("Moving location to specified region",
						"Successfully moved location to specified region", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, Region r, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Region.create(r);
				reportStepDetails("Creating region with specified region: " + FieldName,
						"Successfully created region " + FieldName + " with specified region", PASS);
				break;
			case setRect:
				reg.setRect(r);
				reportStepDetails("Setting rectangle with specified region: " + FieldName,
						"Successfully set rectangle " + FieldName + "  with specified region", PASS);
				break;
			case setROI:
				reg.setROI(r);
				reportStepDetails("Setting rectangle with specified region: " + FieldName,
						"Successfully set rectangle " + FieldName + " with specified region", PASS);
				break;
			case toJythonRegion:
				returnObj = Region.toJythonRegion(r);
				reportStepDetails("Creating a duplicate region as specified region: " + FieldName,
						"Successfully created region " + FieldName + " as specified region", PASS);
				break;
			case morphTo:
				returnObj = reg.morphTo(r);
				reportStepDetails("Moving location to specified region: " + FieldName,
						"Successfully moved location of " + FieldName + " specified region", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String sourcetarget, String destinationtarger,
			float similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(sourcetarget);
			String DesImagePath = GetImagePath(destinationtarger);

			switch (enumCommand) {

			case dragDrop:
				returnObj = reg.dragDrop(new Pattern(SourceImagePath).similar((float) similarityValue),
						new Pattern(DesImagePath).similar((float) similarityValue));
				reportStepDetails("Dragging and dropping image from source to destination:",
						"Successfully drag and drop image  from source to destination", PASS);
				break;
			case paste:
				String value1 = getExcelData(destinationtarger);
				returnObj = reg.paste(SourceImagePath, value1);
				reportStepDetails("Setting value to target", "Successfully  set " + value1 + " value to target", PASS);
				break;
			case type:
				String value = getExcelData(destinationtarger);
				returnObj = reg.paste(SourceImagePath, value);
				reportStepDetails("Setting value to target", "Successfully  set " + value + " value to target", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, SikuliEventObserver observer) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case onChange:

				reg.onChange(observer);
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, SikuliEventObserver observer, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case onChange:

				reg.onChange(observer);
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(FieldName);

			switch (enumCommand) {
			// FieldName is convert to ImagePth for "Doubleclick"

			case click:
				returnObj = reg.click(new Pattern(ImagePath).similar((float) 0.7));
				// returnObj = reg.doubleClick(ImagePath);
				reportStepDetails("Double Clicking on Button/Image of field: ",
						"Successfully double clicked on Button/Image of field", PASS);
				break;

			case doubleClick:
				returnObj = reg.doubleClick(new Pattern(ImagePath).similar((float) 0.7));
				// returnObj = reg.doubleClick(ImagePath);
				reportStepDetails("Double Clicking on Button/Image of field: ",
						"Successfully double clicked on Button/Image of field", PASS);
				break;

			case below:
				returnObj = reg.below();
				reportStepDetails("Getting below coordinates of referenced image: " + FieldName,
						"Successfully get below coordinates " + FieldName + " referenced image", PASS);
				break;

			case above:
				returnObj = reg.above();
				reportStepDetails("Getting above coordinates of referenced image: " + FieldName,
						"Successfully get above coordinates " + FieldName + " referenced image", PASS);
				break;
			case getAutoWaitTimeout:
				returnObj = reg.getAutoWaitTimeout();
				reportStepDetails("Getting auto wait timeout ", "Successfully get auto wait timeout", PASS);
				break;

			case getBottomLeft:
				returnObj = reg.getBottomLeft();
				reportStepDetails("Getting bottom left corner coordinates of referenced image: " + FieldName,
						"Successfully get bottom corner left coordinates  " + FieldName + "  referenced image", PASS);
				break;
			case getBottomRight:
				returnObj = reg.getBottomRight();
				reportStepDetails("Getting bottom right corner coordinates of referenced image: " + FieldName,
						"Successfully get bottom right corner coordinates  " + FieldName + "  referenced image", PASS);
				break;

			case getCenter:
				returnObj = reg.getCenter();
				reportStepDetails("Getting  center corner coordinates of referenced image: " + FieldName,
						"Successfully get center corner coordinates  " + FieldName + "  referenced image", PASS);
				break;
			case getFindFailedResponse:
				returnObj = reg.getFindFailedResponse();
				reportStepDetails("Getting type of execution", "Successfully get type of execution", PASS);
				break;

			case getW:
				returnObj = reg.getW();
				reportStepDetails("Getting width of referenced image: " + FieldName,
						"Successfully get width " + FieldName + " of  referenced image", PASS);
				break;

			case getH:
				returnObj = reg.getH();
				reportStepDetails("Getting height of referenced image: " + FieldName,
						"Successfully get height  " + FieldName + " of referenced image", PASS);
				break;

			case getX:
				returnObj = reg.getX();
				reportStepDetails("Getting x-axis of referenced image: " + FieldName,
						"Successfully get x-axis  " + FieldName + " of referenced image", PASS);
				break;

			case getY:
				returnObj = reg.getY();
				reportStepDetails("Getting y-axis of referenced image: " + FieldName,
						"Successfully get y-axis  " + FieldName + " of referenced image", PASS);
				break;

			case getRect:
				returnObj = reg.getRect();
				reportStepDetails("Getting rectangle of referenced image",
						"Successfully get rectangle of referenced image", PASS);
				break;
			case getROI:
				returnObj = reg.getROI();
				reportStepDetails("Getting rectangle of referenced image",
						"Successfully get rectangle of referenced image", PASS);
				break;

			case getScreen:
				returnObj = reg.getScreen();
				reportStepDetails("Getting screen of referenced image", "Successfully get screen of referenced image",
						PASS);
				break;

			case getLastMatch:
				returnObj = reg.getLastMatch();
				reportStepDetails("Getting last match of referenced image: " + FieldName,
						"Successfully get last matched Image  " + FieldName + " of referenced image", PASS);
				break;
			case getLastMatches:
				returnObj = reg.getLastMatches();
				reportStepDetails("Getting last matches of referenced image: " + FieldName,
						"Successfully get last matches Image  " + FieldName + " of referenced image", PASS);
				break;
			case highlight:
				reg.highlight();
				reportStepDetails("Highlighting referenced image: " + FieldName,
						"Successfully highlighted  " + FieldName + " referenced image", PASS);
				break;

			case inside:
				returnObj = reg.inside();
				reportStepDetails("Checking smaller portion of image similarity",
						"Successfully checked smaller portion of image similaity", PASS);
				break;

			case keyUp:
				reg.keyUp();
				reportStepDetails("Releasing keys specified in value", "Successfully released keys specified in value",
						PASS);
				break;

			case mouseUp:
				reg.mouseUp();
				reportStepDetails("Releasing mouse button specified by value",
						"Successfully release mouse button specified by value", PASS);
				break;

			case left:
				returnObj = reg.left();
				reportStepDetails("Getting left coordinates of referenced image: " + FieldName,
						"Successfully get left coordinates  " + FieldName + " of referenced image", PASS);
				break;

			case right:
				returnObj = reg.right();
				reportStepDetails("Getting right coordinates of referenced image: " + FieldName,
						"Successfully get right coordinates  " + FieldName + " of referenced image", PASS);
				break;
			case nearby:
				returnObj = reg.nearby();
				reportStepDetails("Getting nearby region of referenced image: " + FieldName,
						"Successfully nearby region  " + FieldName + " of referenced image", PASS);
				break;

			case observe:
				reg.observe();

			case listText:
				// returnObj=reg.listText();

			case text:
				returnObj = reg.text();
				reportStepDetails("Getting text present in the referenced image:" + FieldName,
						"Successfully get text present in the  " + FieldName + "  referenced image", PASS);
				break;

			case getTopLeft:
				returnObj = reg.getTopLeft();
				reportStepDetails("Getting top left coordinates of referenced image: " + FieldName,
						"Successfully get top left coordinates  " + FieldName + " of referenced image", PASS);
				break;

			case getTopRight:
				returnObj = reg.getTopRight();
				reportStepDetails("Getting top right coordinates of referenced image: " + FieldName,
						"Successfully get top right coordinates " + FieldName + " of referenced image", PASS);
				break;

			case getThrowException:
				returnObj = reg.getThrowException();
				reportStepDetails("Getting throw exception status", "Successfully get throw exception status", PASS);
				break;

			case stopObserver:
				reg.stopObserver();
				reportStepDetails("Stopping observer", "Successfully stop observer", PASS);
				break;

			case toString:
				returnObj = reg.toString();

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String target, double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);

			switch (enumCommand) {
			case click:
				returnObj = reg.click(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Double Clicking on Button/Image of field: ",
						"Successfully double clicked on Button/Image of field", PASS);
				break;
			case drag:
				returnObj = reg.drag(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Dragging the image", "Successfully dragged the image", PASS);
				break;
			case doubleClick:
				returnObj = reg.doubleClick(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Double Clicking on Button/Image of field: ",
						"Successfully double clicked on Button/Image of field", PASS);
				break;
			case dragAt:
				returnObj = reg.dropAt(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Dropping the image", "Successfully droped the image", PASS);
				break;
			case exists:
				returnObj = reg.exists(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Checking  Button/Image existence  ",
						"Successfully checked the Button/Image of a field exists ", PASS);
				break;
			case find:
				returnObj = reg.find(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;
			case findAll:
				returnObj = reg.findAll(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;

			case findAllNow:
				returnObj = reg.findAllNow(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;

			case getLocationFromPSRML:
				returnObj = reg.getLocationFromPSRML(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Finding given target where it is located", "Successfully find target location",
						PASS);
				break;

			case getRegionFromPSRM:
				returnObj = reg.getRegionFromPSRM(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Finding given target where it is located in region",
						"Successfully find target location in region", PASS);
				break;
			case hover:
				returnObj = reg.hover(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Checking mouse cursor placed on given target",
						"Successfully checked mouse cursor placed on given target", PASS);
				break;

			case mouseMove:
				returnObj = reg.mouseMove(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Checking mouse moved on given target",
						"Successfully checked mouse moved  on given target", PASS);
				break;
			case keyDown:
				reg.keyDown(target);
				reportStepDetails("Pressing keydown specified in value",
						"Successfully press down the key specified in value", PASS);
				break;
			case keyUp:
				screen.keyUp(target);
				reportStepDetails("Releasing keys specified in value", "Successfully released keys specified in value",
						PASS);
				break;
			case rightClick:
				returnObj = reg.rightClick(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Clicking mouse right buttonon Button/Image of field",
						"Successfully clicked mouse right Button on Button/Image of field", PASS);
				break;
			case paste:
				String value1 = getExcelData(target);
				returnObj = reg.paste(value1);
				reportStepDetails("Setting value to target", "Successfully  set value to target", PASS);
				break;
			case type:
				String value = getExcelData(target);
				returnObj = reg.type(value);
				reportStepDetails("Setting value to target", "Successfully  set value to target", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String target, double similarityValue, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);

			switch (enumCommand) {
			case click:
				returnObj = reg.click(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Double Clicking on Button/Image of field: " + FieldName,
						"Successfully double clicked on " + FieldName + " Button/Image of field", PASS);
				break;
			case drag:
				returnObj = reg.drag(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Dragging the image", "Successfully dragged the image", PASS);
				break;
			case doubleClick:
				returnObj = reg.doubleClick(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Double Clicking on Button/Image of field: " + FieldName,
						"Successfully double clicked on " + FieldName + " Button/Image of field", PASS);
				break;
			case dragAt:
				returnObj = reg.dropAt(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Dropping the image" + FieldName, "Successfully dropped the image", PASS);
				break;
			case exists:
				returnObj = reg.exists(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Checking  Button/Image existence:  " + FieldName,
						"Successfully checked the " + FieldName + " Button/Image of a field exists ", PASS);
				break;
			case find:
				returnObj = reg.find(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Finding correct image match of the  given pattern on the screen: " + FieldName,
						"Successfully find correct " + FieldName + " image match on the screen", PASS);
				break;
			case findAll:
				returnObj = reg.findAll(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Finding correct image match of the  given pattern on the screen: " + FieldName,
						"Successfully find correct " + FieldName + " image match on the screen", PASS);
				break;

			case findAllNow:
				returnObj = reg.findAllNow(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Finding correct image match of the  given pattern on the screen : " + FieldName,
						"Successfully find correct " + FieldName + " image match on the screen", PASS);
				break;

			case getLocationFromPSRML:
				returnObj = reg.getLocationFromPSRML(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Finding given target where it is located: " + FieldName,
						"Successfully find " + FieldName + " target location", PASS);
				break;

			case getRegionFromPSRM:
				returnObj = reg.getRegionFromPSRM(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Finding given target where it is located in region: " + FieldName,
						"Successfully find " + FieldName + " target location in region", PASS);
				break;
			case hover:
				returnObj = reg.hover(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Checking mouse cursor placed on given target",
						"Successfully checked mouse cursor placed on given target", PASS);
				break;

			case mouseMove:
				returnObj = reg.mouseMove(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Checking mouse moved on given target",
						"Successfully checked mouse moved on given target", PASS);
				break;
			case keyDown:
				reg.keyDown(target);
				reportStepDetails("Pressing keydown specified in value: " + FieldName,
						"Successfully press down the " + FieldName + " key specified in value", PASS);
				break;
			case keyUp:
				screen.keyUp(target);
				reportStepDetails("Releasing keys specified in value: " + FieldName,
						"Successfully released " + FieldName + " keys specified in value", PASS);
				break;
			case rightClick:
				returnObj = reg.rightClick(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Clicking mouse right button on Button/Image of field: " + FieldName,
						"Successfully clicked mouse right " + FieldName + " Button on Button/Image of field", PASS);
				break;
			case paste:
				String value1 = getExcelData(target);
				returnObj = reg.paste(value1);
				reportStepDetails("Setting value to target", "Successfully  set value to target", PASS);
				break;
			case type:
				String value = getExcelData(target);
				returnObj = reg.type(value);
				reportStepDetails("Setting value to target", "Successfully  set value to target", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String target, double delaytime, double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);

			String ImagePath = GetImagePath(target);
			switch (enumCommand) {

			case dropAt:
				// returnObj = reg.dropAt(ImagePath, delaytime);
				returnObj = reg.dropAt(new Pattern(ImagePath).similar((float) similarityValue), delaytime);
				reportStepDetails("Dropping  Button/Image ", "Successfully droped the Button/Image of a field  ", PASS);
				break;
			case exists:
				// returnObj = reg.exists(ImagePath, delaytime);
				returnObj = reg.exists(new Pattern(ImagePath).similar((float) similarityValue), delaytime);
				reportStepDetails("Checking  Button/Image existence  ",
						"Successfully checked the Button/Image of a field exists ", PASS);
				break;

			case wait:
				// returnObj = reg.wait(ImagePath, delaytime);
				returnObj = reg.wait(new Pattern(ImagePath).similar((float) similarityValue), delaytime);

				reportStepDetails("Checking process waits until Button/Image of field appears",
						"Successfully waits process until Button/Image of field appears", PASS);
				break;
			case waitVanish:
				returnObj = reg.waitVanish(ImagePath, delaytime);
				returnObj = reg.waitVanish(new Pattern(ImagePath).similar((float) similarityValue), delaytime);

				reportStepDetails("Checking process waits until Button/Image of field vanish",
						"Successfully waits process until Button/Image of field vanish", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String target, double delaytime, String FieldName,
			double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);
			switch (enumCommand) {

			case dropAt:
				// returnObj = reg.dropAt(ImagePath, delaytime);
				returnObj = reg.dropAt(new Pattern(ImagePath).similar((float) similarityValue), delaytime);
				reportStepDetails("Dropping  Button/Image: " + FieldName,
						"Successfully droped " + FieldName + " the Button/Image of a field  ", PASS);
				break;
			case exists:
				// returnObj = reg.exists(ImagePath, delaytime);
				returnObj = reg.exists(new Pattern(ImagePath).similar((float) similarityValue), delaytime);
				reportStepDetails("Checking  Button/Image existence :" + FieldName,
						"Successfully checked " + FieldName + " the Button/Image of a field exists ", PASS);
				break;
			case wait:
				// returnObj = reg.wait(ImagePath, delaytime);
				returnObj = reg.wait(new Pattern(ImagePath).similar((float) similarityValue), delaytime);
				reportStepDetails("Checking process waits until Button/Image of field appears: " + FieldName,
						"Successfully waits process until " + FieldName + " Button/Image of field appears", PASS);
				break;
			case waitVanish:
				returnObj = reg.waitVanish(ImagePath, delaytime);
				returnObj = reg.waitVanish(new Pattern(ImagePath).similar((float) similarityValue), delaytime);

				reportStepDetails("Checking process waits until Button/Image of field vanish:" + FieldName,
						"Successfully waits process until " + FieldName + " Button/Image of field vanish", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String target, int modifier, double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);
			switch (enumCommand) {

			case click:
				// returnObj = reg.click(ImagePath, modifier);
				returnObj = reg.click(new Pattern(ImagePath).similar((float) similarityValue), modifier);
				reportStepDetails("Clicking on Button/Image of field", "Successfully clicked on Button/Image of field",
						PASS);
				break;
			case clickAndWait:
				returnObj = reg.click(new Pattern(ImagePath).similar((float) similarityValue), modifier);
				synchronized (reg) {
					reg.wait(30);
				}

				captureScreenShot();
				reportStepDtlsWithScreenshot("Clicking Button/Image:",
						"Successfully clicked the Button/Image of a field  ", PASS);
				break;

			case doubleClick:
				// returnObj = reg.doubleClick(ImagePath, modifier);
				returnObj = reg.doubleClick(new Pattern(ImagePath).similar((float) similarityValue), modifier);
				reportStepDetails("Double Clicking on Button/Image of field",
						"Successfully double clicked on Button/Image of field", PASS);
				break;
			case rightClick:
				// returnObj = reg.rightClick(ImagePath, modifier);
				returnObj = reg.rightClick(new Pattern(ImagePath).similar((float) similarityValue), modifier);
				reportStepDetails("Clicking mouse right buttonon Button/Image of field",
						"Successfully clicked mouse right Button on Button/Image of field", PASS);
				break;
			case type:
				String value = getExcelData(target);
				returnObj = reg.type(value, modifier);
				reportStepDetails("Setting value to target", "Successfully  set  " + value + "  value to target", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String target, int modifier, double similarityValue,
			String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);
			switch (enumCommand) {

			case click:
				// returnObj = reg.click(ImagePath, modifier);
				returnObj = reg.click(new Pattern(ImagePath).similar((float) similarityValue), modifier);
				reportStepDetails("Clicking on Button/Image of field: " + FieldName,
						"Successfully clicked on  " + FieldName + "  Button/Image of field", PASS);
				break;
			case clickAndWait:
				returnObj = reg.click(new Pattern(ImagePath).similar((float) similarityValue), modifier);
				synchronized (reg) {
					reg.wait(30);
				}
				captureScreenShot();
				reportStepDtlsWithScreenshot("Clicking Button/Image: " + FieldName,
						"Successfully clicked the  " + FieldName + " Button/Image of a field  ", PASS);
				break;

			case doubleClick:
				// returnObj = reg.doubleClick(ImagePath, modifier);
				returnObj = reg.doubleClick(new Pattern(ImagePath).similar((float) similarityValue), modifier);
				reportStepDetails("Double Clicking on Button/Image of field: " + FieldName,
						"Successfully double clicked on " + FieldName + " Button/Image of field", PASS);
				break;
			case rightClick:
				// returnObj = reg.rightClick(ImagePath, modifier);
				returnObj = reg.rightClick(new Pattern(ImagePath).similar((float) similarityValue), modifier);
				reportStepDetails("Clicking mouse right buttonon Button/Image of field: " + FieldName,
						"Successfully clicked mouse right Button on Button/Image of field", PASS);
				break;
			case type:
				String value = getExcelData(target);
				returnObj = reg.type(value, modifier);
				reportStepDetails("Setting value to target: " + FieldName,
						"Successfully  set  " + value + "  value to target", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String target, int direction, int steps, double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);
			switch (enumCommand) {

			case wheel:
				returnObj = reg.wheel(new Pattern(ImagePath).similar((float) similarityValue), direction, steps);

				// returnObj = reg.wheel(ImagePath, direction, steps);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String target, int direction, int steps, String FieldName,
			double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);
			switch (enumCommand) {

			case wheel:
				returnObj = reg.wheel(new Pattern(ImagePath).similar((float) similarityValue), direction, steps);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String target, int modifier, String FieldName,
			double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);
			System.out.println("ImagePath" + ImagePath);
			switch (enumCommand) {

			case click:
				// returnObj = reg.click(ImagePath, modifier);
				returnObj = reg.click(new Pattern(ImagePath).similar((float) similarityValue), modifier);

				reportStepDetails("Clicking on Button/Image of field: " + FieldName,
						"Successfully clicked on " + FieldName + " Button/Image of field", PASS);
				break;
			case clickAndWait:
				// returnObj = reg.click(ImagePath, modifier);
				returnObj = reg.click(new Pattern(ImagePath).similar((float) similarityValue), modifier);

				synchronized (reg) {
					reg.wait(30);
				}
				captureScreenShot();
				reportStepDtlsWithScreenshot("Clicking Button/Image:",
						"Successfully clicked the Button/Image of a field  ", PASS);
				break;

			case doubleClick:
				// returnObj = reg.doubleClick(ImagePath, modifier);
				returnObj = reg.doubleClick(new Pattern(ImagePath).similar((float) similarityValue), modifier);

				reportStepDetails("Double Clicking on Button/Image of field: " + FieldName,
						"Successfully double clicked on " + FieldName + " Button/Image of field", PASS);
				break;
			case rightClick:
				// returnObj = reg.rightClick(ImagePath, modifier);
				returnObj = reg.rightClick(new Pattern(ImagePath).similar((float) similarityValue), modifier);

				reportStepDetails("Clicking mouse right buttonon Button/Image of field: " + FieldName,
						"Successfully clicked mouse right Button " + FieldName + " on Button/Image of field", PASS);
				break;
			case type:
				String value = getExcelData(target);
				returnObj = reg.type(value, modifier);
				reportStepDetails("Setting value to target: " + FieldName,
						"Successfully set value " + value + " to target", PASS);
				break;
			case wait:
				returnObj = reg.wait(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Checking process waits until Button/Image of field appears",
						"Successfully waits process until Button/Image of field appears", PASS);
				break;
			case waitVanish:
				returnObj = screen.waitVanish(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Checking process waits until Button/Image of field vanish",
						"Successfully waits process until Button/Image of field vanish", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String PSCtarget, SikuliEventObserver observer,
			double similarityValue) {

		Object returnObj = null;
		String ImagePath = GetImagePath(PSCtarget);
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case onAppear:

				// reg.onAppear(PSCtarget, observer);
				reg.onAppear(new Pattern(ImagePath).similar((float) similarityValue), observer);

			case onVanish:

				// reg.onVanish(PSCtarget, observer);
				reg.onVanish(new Pattern(ImagePath).similar((float) similarityValue), observer);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String PSCtarget, SikuliEventObserver observer, String FieldName,
			double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(PSCtarget);
			switch (enumCommand) {

			case onAppear:

				// reg.onAppear(PSCtarget, observer);
				reg.onAppear(new Pattern(ImagePath).similar((float) similarityValue), observer);

			case onVanish:

				// reg.onVanish(PSCtarget, observer);
				reg.onVanish(new Pattern(ImagePath).similar((float) similarityValue), observer);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String sourcetarget, String obj, double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(sourcetarget);
			System.out.println("SourceImagePathSourceImagePath" + SourceImagePath);
			String DesImagePath = GetImagePath(obj);
			switch (enumCommand) {

			case doubleClick:
				returnObj = reg.doubleClick(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Double Clicking on Button/Image of field: " + obj,
						"Successfully double clicked on " + obj + " Button/Image of field", PASS);
				break;
			case drag:
				returnObj = reg.drag(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Dragging the image" + obj, "Successfully dragged the image" + obj, PASS);
				break;

			case dragDrop:
				returnObj = reg.dragDrop(new Pattern(SourceImagePath).similar((float) similarityValue),
						new Pattern(DesImagePath).similar((float) similarityValue));
				reportStepDetails("Dragging and dropping image from source to destination: " + obj,
						"Successfully drag and drop image " + obj + " from source to destination", PASS);
				break;
			case type:
				String value = getExcelData(obj);
				returnObj = reg.type(new Pattern(SourceImagePath).similar((float) similarityValue), value);
				reportStepDetails("Setting value to target: " + obj, "Successfully  set " + value + "value to target",
						PASS);
				break;
			case paste:
				String value1 = getExcelData(obj);
				returnObj = reg.paste(new Pattern(SourceImagePath).similar((float) similarityValue), value1);
				reportStepDetails("Setting value to target: " + obj, "Successfully  set " + value1 + " value to target",
						PASS);
				break;
			case getRegionFromPSRM:
				returnObj = reg.getRegionFromPSRM(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Finding given target where it is located in region: " + obj,
						"Successfully find target location in region", PASS);
				break;

			case click:
				returnObj = reg.click(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Double Clicking on Button/Image of field: " + obj,
						"Successfully double clicked on " + obj + " Button/Image of field", PASS);
				break;

			case dragAt:
				returnObj = reg.dropAt(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Dropping the image: " + obj, "Successfully droped " + obj + " the image", PASS);
				break;
			case exists:
				returnObj = reg.exists(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Checking  Button/Image existence  : " + obj,
						"Successfully checked the " + obj + " Button/Image of a field exists ", PASS);
				break;
			case find:
				returnObj = reg.find(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Finding correct image match of the  given pattern on the screen : " + obj,
						"Successfully find correct image " + obj + " match on the screen", PASS);
				break;
			case findAll:
				returnObj = reg.findAll(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Finding correct image match of the  given pattern on the screen : " + obj,
						"Successfully find correct image match " + obj + " on the screen", PASS);
				break;

			case findAllNow:
				returnObj = reg.findAllNow(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Finding correct image match of the  given pattern on the screen: " + obj,
						"Successfully find correct image match " + obj + " on the screen", PASS);
				break;

			case getLocationFromPSRML:
				returnObj = reg.getLocationFromPSRML(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Finding given target where it is located: " + obj,
						"Successfully find target location", PASS);
				break;

			case hover:
				returnObj = reg.hover(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Checking mouse cursor placed on given target: " + obj,
						"Successfully checked mouse cursor placed on given target", PASS);
				break;

			case mouseMove:
				returnObj = reg.mouseMove(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Checking mouse moved on given target: " + obj,
						"Successfully checked mouse moved  " + obj + " on given target", PASS);
				break;
			case keyDown:
				reg.keyDown(sourcetarget);
				reportStepDetails("Pressing keydown specified in value: " + obj,
						"Successfully press down the key " + obj + " specified in value", PASS);
				break;
			case keyUp:
				screen.keyUp(sourcetarget);
				reportStepDetails("Releasing keys specified in value: " + obj,
						"Successfully released keys " + obj + " specified in value", PASS);
				break;
			case rightClick:
				returnObj = reg.rightClick(new Pattern(SourceImagePath).similar((float) similarityValue));
				reportStepDetails("Clicking mouse right buttonon Button/Image of field: " + obj,
						"Successfully clicked mouse right Button on " + obj + " Button/Image of field", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String sourcetarget, String obj, int modifiers,
			double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(sourcetarget);
			String DesImagePath = GetImagePath(obj);
			switch (enumCommand) {

			case dragDrop:
				// obj is the destination
				// returnObj = reg.dragDrop(SourceImagePath, DesImagePath,
				// modifiers);
				returnObj = reg.dragDrop(new Pattern(SourceImagePath).similar((float) similarityValue),
						new Pattern(DesImagePath).similar((float) similarityValue), modifiers);

				reportStepDetails("Dragging and dropping image from source to destination",
						"Successfully drag and drop image from source to destination", PASS);
				break;

			case type:
				// Obj is the text
				String value = getExcelData(obj);
				returnObj = reg.type(new Pattern(SourceImagePath).similar((float) similarityValue), value, modifiers);

				// returnObj = reg.type(SourceImagePath, value, modifiers);
				reportStepDetails("Setting value to target", "Successfully  set " + value + " value to target", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String sourcetarget, String obj, int modifiers, String FieldName,
			double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(sourcetarget);
			String DesImagePath = GetImagePath(obj);
			// System.out.println("ImagePath"+ImagePath);
			switch (enumCommand) {

			case dragDrop:
				// obj is the destination
				// returnObj = reg.dragDrop(SourceImagePath, DesImagePath,
				// modifiers);
				returnObj = reg.dragDrop(new Pattern(SourceImagePath).similar((float) similarityValue),
						new Pattern(DesImagePath).similar((float) similarityValue), modifiers);

				reportStepDetails("Dragging and dropping image from source to destination: " + FieldName,
						"Successfully drag and drop image " + FieldName + " from source to destination", PASS);
				break;

			case type:
				// obj is the text
				String value = getExcelData(obj);
				// returnObj = reg.type(SourceImagePath, value, modifiers);
				returnObj = reg.type(new Pattern(SourceImagePath).similar((float) similarityValue), value, modifiers);

				reportStepDetails("Setting value to target :" + FieldName,
						"Successfully  set value " + value + " to target", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(Region reg, String command, String sourcetarget, String obj, String FieldName,
			double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(sourcetarget);
			String DesImagePath = GetImagePath(obj);
			switch (enumCommand) {

			case dragDrop:
				// returnObj = reg.dragDrop(SourceImagePath, DesImagePath);
				returnObj = reg.dragDrop(new Pattern(SourceImagePath).similar((float) similarityValue),
						new Pattern(DesImagePath).similar((float) similarityValue));

				reportStepDetails("Dragging and dropping image from source to destination: " + FieldName,
						"Successfully drag and drop image " + FieldName + " from source to destination", PASS);
				break;
			case type:
				// obj is the text
				String value = getExcelData(obj);
				returnObj = reg.type(new Pattern(SourceImagePath).similar((float) similarityValue), value);

				// returnObj = reg.type(SourceImagePath, value);

				reportStepDetails("Setting value to target" + FieldName,
						"Successfully  set value " + value + " to target", PASS);
				break;
			case paste:
				// obj is the text
				String value1 = getExcelData(obj);
				returnObj = reg.paste(new Pattern(SourceImagePath).similar((float) similarityValue), value1);

				// returnObj = reg.paste(SourceImagePath, value1);
				reportStepDetails("Setting value to target" + FieldName,
						"Successfully  set value " + FieldName + " to target", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case below:
				returnObj = screen.below();
				reportStepDetails("Getting below coordinates of referenced image",
						"Successfully get below coordinates referenced image", PASS);
				break;

			case above:
				returnObj = screen.above();
				reportStepDetails("Getting above coordinates of referenced image",
						"Successfully get above coordinates of referenced image", PASS);
				break;

			case getAutoWaitTimeout:
				returnObj = screen.getAutoWaitTimeout();
				reportStepDetails("Getting auto wait timeout ", "Successfully get auto wait timeout", PASS);
				break;

			case getBottomLeft:
				returnObj = screen.getBottomLeft();
				reportStepDetails("Getting bottom left corner coordinates of referenced image",
						"Successfully get bottom left corner coordinates of referenced image", PASS);
				break;
			case getBottomRight:
				returnObj = screen.getBottomRight();
				reportStepDetails("Getting bottom right corner coordinates of referenced image",
						"Successfully get bottom right corner coordinates of referenced image", PASS);
				break;

			case getCenter:
				returnObj = screen.getCenter();
				reportStepDetails("Getting  center corner coordinates of referenced image",
						"Successfully get center corner coordinates of referenced image", PASS);
				break;
			case getFindFailedResponse:
				returnObj = screen.getFindFailedResponse();
				reportStepDetails("Getting type of execution", "Successfully get type of execution", PASS);
				break;

			case getW:
				returnObj = screen.getW();
				reportStepDetails("Getting width of referenced image", "Successfully get width of referenced image",
						PASS);
				break;
			case getH:
				returnObj = screen.getH();
				reportStepDetails("Getting height of referenced image", "Successfully get height of referenced image",
						PASS);
				break;
			case getX:
				returnObj = screen.getX();
				reportStepDetails("Getting x-axis coordinates of referenced image",
						"Successfully get x-axis coordinates of referenced image", PASS);
				break;

			case getY:
				returnObj = screen.getY();
				reportStepDetails("Getting y-axis coordinates of referenced image",
						"Successfully get y-axis coordinates of referenced image", PASS);
				break;

			case getRect:
				returnObj = screen.getRect();
				reportStepDetails("Getting rectangle coordinates of referenced image",
						"Successfully get rectangle coordinates of referenced image", PASS);
				break;
			case getROI:
				returnObj = screen.getROI();
				reportStepDetails("Getting rectangle coordinates of referenced image",
						"Successfully get rectangle coordinates of referenced image", PASS);
				break;
			case getScreen:
				returnObj = screen.getScreen();
				reportStepDetails("Getting screen object of referenced image",
						"Successfully get screen object of referenced image", PASS);
				break;

			case getLastMatch:
				returnObj = screen.getLastMatch();
				reportStepDetails("Getting best match of referenced image",
						"Successfully get best matched Image of referenced image", PASS);
				break;

			case getLastMatches:
				returnObj = screen.getLastMatches();
				reportStepDetails("Getting best matches of referenced image",
						"Successfully get best matches Image of referenced image", PASS);
				break;

			case highlight:
				screen.highlight();
				reportStepDetails("Highlighting referenced image", "Successfully highlighted referenced image", PASS);
				break;
			case inside:
				returnObj = screen.inside();
				reportStepDetails("Checking smaller portion of image similarity",
						"Successfully checked smaller portion of image similaity", PASS);
				break;

			case keyUp:
				screen.keyUp();
				reportStepDetails("Releasing keys specified in value", "Successfully released keys specified in value",
						PASS);
				break;
			case mouseUp:
				screen.mouseUp();
				reportStepDetails("Releasing mouse button specified by value",
						"Successfully release mouse button specified by value", PASS);
				break;
			case left:
				returnObj = screen.left();
				reportStepDetails("Getting left corner coordinates of referenced image",
						"Successfully get left corner coordinates of referenced image", PASS);
				break;
			case right:
				returnObj = screen.right();
				reportStepDetails("Getting right corner coordinates of referenced image",
						"Successfully get right corner coordinates of referenced image", PASS);
				break;
			case nearby:
				returnObj = screen.nearby();
				reportStepDetails("Getting nearby region of referenced image",
						"Successfully nearby region of referenced image", PASS);
				break;

			case observe:
				screen.observe();

			case listText:
				// returnObj=screen.listText();

			case text:
				returnObj = screen.text();
				reportStepDetails("Getting text present in the referenced image",
						"Successfully get text present in the  referenced image", PASS);
				break;

			case getTopLeft:
				returnObj = screen.getTopLeft();
				reportStepDetails("Getting top left corner coordinates of referenced image",
						"Successfully get top corner left coordinates of referenced image", PASS);
				break;

			case getTopRight:
				returnObj = screen.getTopRight();
				reportStepDetails("Getting top right corner coordinates of referenced image",
						"Successfully get top corner right coordinates of referenced image", PASS);
				break;
			case getThrowException:
				returnObj = screen.getThrowException();
				reportStepDetails("Getting throw exception status", "Successfully get throw exception status", PASS);
				break;
			case stopObserver:
				screen.stopObserver();
				reportStepDetails("Stopping observer", "Successfully stop observer", PASS);
				break;
			case toString:
				returnObj = screen.toString();
			case capture:
				returnObj = screen.capture();
				reportStepDetails("Capturing screen shot of  referenced image",
						"Successfully captured  referenced image", PASS);
				break;
			case getBounds:
				returnObj = screen.getBounds();
				reportStepDetails("Getting bounds of referenced image", "Successfully get bounds of referenced image",
						PASS);
				break;
			case getID:
				returnObj = screen.getID();
				reportStepDetails("Getting ID of monitor", "Successfully get ID of monitor", PASS);
				break;
			case getNumberScreens:
				returnObj = Screen.getNumberScreens();
				reportStepDetails("Getting number of screens available", "Successfully get number of screens available",
						PASS);
				break;
			case getPrimaryId:
				returnObj = Screen.getPrimaryId();
				reportStepDetails("Getting primaryID of current screen", "Successfully get primaryID of current screen",
						PASS);
				break;
			case getRobot:
				returnObj = screen.getRobot();
				reportStepDetails("Getting desktop robot ", "Successfully get desktop robot ", PASS);
				break;
			case initBounds:
				// returnObj=screen.initBounds();
				reportStepDetails("Getting initbounds of referenced image",
						"Successfully get initbounds of referenced image", PASS);
				break;
			case selectRegion:
				returnObj = screen.selectRegion();
				reportStepDetails("selecting region of referenced image at runtime",
						"Successfully select region of referenced image at runtime", PASS);
				break;
			case userCapture:
				returnObj = screen.userCapture();
				reportStepDetails("Capturing user specified image at runtime",
						"Successfully captured user specified image at runtime", PASS);
				break;

			case getGraphicsDevice:
				returnObj = screen.getGraphicsDevice();
				reportStepDetails("Getting graphics device of referenced image",
						"Successfully get graphic device of referenced image", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, boolean flag) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case setThrowException:
				screen.setThrowException(flag);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, boolean flag, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case setThrowException:
				screen.setThrowException(flag);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, double secs) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case observe:
				screen.observe(secs);
			case observeInBackground:
				screen.observeInBackground(secs);
			case setAutoWaitTimeout:
				screen.setAutoWaitTimeout(secs);
				reportStepDetails("Increasing the auto wait time as specified target ",
						"Successfully Increased the auto wait time as specified target", PASS);
				break;
			case wait:
				screen.wait(secs);
				reportStepDetails("Checking process waits until Button/Image of field appears",
						"Successfully waits process until Button/Image of field appears", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, double secs, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case observe:
				screen.observe(secs);
			case observeInBackground:
				screen.observeInBackground(secs);
			case setAutoWaitTimeout:
				screen.setAutoWaitTimeout(secs);
				reportStepDetails("Increasing the auto wait time as specified target :" + FieldName,
						"Successfully Increased " + secs + " secs the auto wait time as specified target", PASS);
				break;
			case wait:
				screen.wait(secs);
				reportStepDetails("Checking process waits until Button/Image of field appears: " + FieldName,
						"Successfully waits process until " + FieldName + " Button/Image of field appears", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, float secs) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case highlight:
				screen.highlight(secs);
				reportStepDetails("Highlighting referenced image", "Successfully highlighted referenced image", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, float secs, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case highlight:
				screen.highlight(secs);
				reportStepDetails("Highlighting referenced image:" + FieldName,
						"Successfully highlighted " + FieldName + " referenced image", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, int value) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case above:
				returnObj = screen.above(value);
				reportStepDetails("Getting above coordinates of referenced image",
						"Successfully get above coordinates of referenced image", PASS);
				break;

			case below:
				returnObj = screen.below(value);
				reportStepDetails("Getting below coordinates of referenced image",
						"Successfully get below coordinates of referenced image", PASS);
				break;
			case left:
				returnObj = screen.left(value);
				reportStepDetails("Getting left coordinates of referenced image",
						"Successfully get left coordinates of referenced image", PASS);
				break;
			case keyDown:
				screen.keyDown(value);
				reportStepDetails("Pressing keydown specified in value",
						"Successfully press down the key specified in value", PASS);
				break;
			case keyUp:
				screen.keyUp(value);
				reportStepDetails("Releasing keys specified in value", "Successfully released keys specified in value",
						PASS);
				break;
			case mouseDown:
				screen.mouseDown(value);
				reportStepDetails("Clicking mouse button specified by value",
						"Successfully click mouse button specified by value", PASS);
				break;
			case mouseUp:
				screen.mouseUp(value);
				reportStepDetails("Releasing mouse button specified by value",
						"Successfully release mouse button specified by value", PASS);
				break;
			case right:
				screen.right(value);
				reportStepDetails("Getting right corner coordinates of referenced image",
						"Successfully get right corner coordinates of referenced image", PASS);
				break;
			case setH:
				screen.setH(value);
				reportStepDetails("Setting Image height as specified by value",
						"Successfully Set Image height as specified by value", PASS);
				break;
			case setW:
				screen.setW(value);
				reportStepDetails("Setting Image width as specified by value",
						"Successfully Set Image width as specified by value", PASS);
				break;
			case setX:
				screen.setX(value);
				reportStepDetails("Setting Image left size as specified by value",
						"Successfully Set left size as specified by value", PASS);
				break;
			case setY:
				screen.setY(value);
				reportStepDetails("Setting Image right size as specified by value",
						"Successfully Set right size as specified by value", PASS);
				break;
			case getBounds:
				returnObj = screen.getBounds();
				reportStepDetails("Getting bounds of referenced image", "Successfully get bounds of referenced image",
						PASS);
				break;
			case getRobot:
				returnObj = screen.getRobot();
				reportStepDetails("Getting desktop robot ", "Successfully get desktop robot ", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, int xaxis, int yaxis, int width, int height) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Screen.create(xaxis, yaxis, width, height);
				reportStepDetails("Creating region with specified coordinates",
						"Successfully create region with specified coordinates", PASS);
				break;
			case setRect:
				screen.setRect(xaxis, yaxis, width, height);
				reportStepDetails("Creating rectangle with specified coordinates",
						"Successfully create rectangle with specified coordinates", PASS);
				break;
			case setROI:
				screen.setROI(xaxis, yaxis, width, height);
				reportStepDetails("Creating rectangle with specified coordinates",
						"Successfully create rectangle with specified coordinates", PASS);
				break;
			case capture:
				returnObj = screen.capture(xaxis, yaxis, width, height);
				reportStepDetails("Capturing screen shot of  referenced image: ",
						"Successfully captured referenced image", PASS);

				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, int xaxis, int yaxis, int width, int height, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Screen.create(xaxis, yaxis, width, height);
				reportStepDetails("Creating region with specified coordinates: " + FieldName,
						"Successfully create region of " + FieldName + " with specified coordinates", PASS);
				break;
			case setRect:
				screen.setRect(xaxis, yaxis, width, height);
				reportStepDetails("Creating rectangle with specified coordinates: " + FieldName,
						"Successfully create rectangle of " + FieldName + " with specified coordinates", PASS);
				break;
			case setROI:
				screen.setROI(xaxis, yaxis, width, height);
				reportStepDetails("Creating rectangle with specified coordinates: " + FieldName,
						"Successfully create rectangle of " + FieldName + " with specified coordinates", PASS);
			case capture:
				returnObj = screen.capture(xaxis, yaxis, width, height);
				reportStepDetails("Capturing screen shot of  referenced image:" + FieldName,
						"Successfully captured " + FieldName + " referenced image", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, int value, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case above:
				returnObj = screen.above(value);
				reportStepDetails("Getting above coordinates of referenced image: " + FieldName,
						"Successfully get above coordinates of " + FieldName + " referenced image", PASS);
				break;

			case below:
				returnObj = screen.below(value);
				reportStepDetails("Getting below coordinates of referenced image: " + FieldName,
						"Successfully get below coordinates of " + FieldName + " referenced image", PASS);
				break;
			case left:
				returnObj = screen.left(value);
				reportStepDetails("Getting left coordinates of referenced image: " + FieldName,
						"Successfully get left coordinates of " + FieldName + " referenced image", PASS);
				break;
			case keyDown:
				screen.keyDown(value);
				reportStepDetails("Pressing keydown specified in value: " + FieldName,
						"Successfully press down " + FieldName + " the key specified in value", PASS);
				break;
			case keyUp:
				screen.keyUp(value);
				reportStepDetails("Releasing keys specified in value: " + FieldName,
						"Successfully released " + FieldName + " keys specified in value", PASS);
				break;
			case mouseDown:
				screen.mouseDown(value);
				reportStepDetails("Clicking mouse button specified by value: " + FieldName,
						"Successfully click mouse " + FieldName + " button specified by value", PASS);
				break;
			case mouseUp:
				screen.mouseUp(value);
				reportStepDetails("Releasing mouse button specified by value: " + FieldName,
						"Successfully release mouse " + FieldName + " button specified by value", PASS);
				break;
			case right:
				screen.right(value);
				reportStepDetails("Getting right corner coordinates of referenced image: " + FieldName,
						"Successfully get right corner coordinates of " + FieldName + " referenced image", PASS);
				break;
			case setH:
				screen.setH(value);
				reportStepDetails("Setting Image height as specified by value: " + FieldName,
						"Successfully Set " + FieldName + " Image height as specified by value", PASS);
				break;
			case setW:
				screen.setW(value);
				reportStepDetails("Setting Image width as specified by value: " + FieldName,
						"Successfully Set " + FieldName + " Image width as specified by value", PASS);
				break;
			case setX:
				screen.setX(value);
				reportStepDetails("Setting Image left size as specified by value: " + FieldName,
						"Successfully Set " + FieldName + " left corner size as specified by value", PASS);
				break;
			case setY:
				screen.setY(value);
				reportStepDetails("Setting Image right size as specified by value: " + FieldName,
						"Successfully Set " + FieldName + " right corner size as specified by value", PASS);
				break;
			case getBounds:
				returnObj = screen.getBounds();
				reportStepDetails("Getting bounds of referenced image: " + FieldName,
						"Successfully get bounds of " + FieldName + " referenced image", PASS);
				break;
			
			 * case getRobot: returnObj = screen.getRobot(); reportStepDetails(
			 * "Getting desktop robot ", "Successfully get desktop robot ",
			 * PASS); break;
			 
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, java.awt.Rectangle rect) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case capture:
				returnObj = screen.capture(rect);
				reportStepDetails("Capturing screen shot of  referenced image: ",
						"Successfully captured referenced image", PASS);
			case create:
				returnObj = Screen.create(rect);
				reportStepDetails("Creating region from the rectangle coordinates",
						"Successfully create region from the rectangle coordinates", PASS);
				break;
			case setROI:
				screen.setROI(rect);
				reportStepDetails("Setting rectangle with specified rectangle",
						"Successfully create region with specified rectangle", PASS);
				break;
			case setRect:
				screen.setRect(rect);
				reportStepDetails("Setting rectangle with specified rectangle",
						"Successfully create region with specified rectangle", PASS);
				break;
			case newRegion:
				screen.newRegion(rect);
				reportStepDetails("Creating region from the rectangle coordinates",
						"Successfully create region from the rectangle coordinates", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, java.awt.Rectangle r, IScreen parentScreen) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Screen.create(r, parentScreen);
				reportStepDetails("Creating region with specified coordinates",
						"Successfully create region with specified coordinates", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, java.awt.Rectangle r, IScreen parentScreen, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Screen.create(r, parentScreen);
				reportStepDetails("Creating region with specified coordinates: " + FieldName,
						"Successfully create " + FieldName + " region with specified coordinates", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, java.awt.Rectangle rect, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Screen.create(rect);
				reportStepDetails("Creating region from the rectangle coordinates: " + FieldName,
						"Successfully create region of " + FieldName + " from the rectangle coordinates", PASS);
				break;
			case setROI:
				screen.setROI(rect);
				reportStepDetails("Setting rectangle with specified rectangle: " + FieldName,
						"Successfully create region of " + FieldName + " with specified rectangle", PASS);
				break;
			case setRect:
				screen.setRect(rect);
				reportStepDetails("Setting rectangle with specified rectangle: " + FieldName,
						"Successfully create region of " + FieldName + " with specified rectangle", PASS);
				break;
			case newRegion:
				screen.newRegion(rect);
				reportStepDetails("Creating region from the rectangle coordinates: " + FieldName,
						"Successfully create region of " + FieldName + " from the rectangle coordinates", PASS);
				break;
			case capture:
				returnObj = screen.capture(rect);
				reportStepDetails("Capturing screen shot of  referenced image: " + FieldName,
						"Successfully captured " + FieldName + " referenced image", PASS);
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, Location Loc) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case showClick:
				screen.showClick(Loc);
				reportStepDetails("Checking given location is highlighted",
						"Successfully checked given location is highlighted", PASS);
				break;
			case showDropTarget:
				screen.showDropTarget(Loc);
			case showMove:
				screen.showMove(Loc);
			case showTarget:
				screen.showTarget(Loc);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, Location Loc, double secs) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case showTarget:
				screen.showTarget(Loc, secs);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, Location Loc, double secs, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case showTarget:
				screen.showTarget(Loc, secs);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, Location Loc, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case showClick:
				screen.showClick(Loc);
				reportStepDetails("Checking given location is highlighted: " + FieldName,
						"Successfully checked " + FieldName + " given location is highlighted", PASS);
				break;
			case showDropTarget:
				screen.showDropTarget(Loc);
			case showMove:
				screen.showMove(Loc);
			case showTarget:
				screen.showTarget(Loc);

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, Region r) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {
			case capture:
				returnObj = screen.capture(r);
				reportStepDetails("Capturing screen shot of  referenced image",
						"Successfully captured  referenced image", PASS);
				break;
			case create:
				returnObj = Screen.create(r);
				reportStepDetails("Creating region with specified region",
						"Successfully created region with specified region", PASS);
				break;
			case setRect:
				screen.setRect(r);
				reportStepDetails("Setting rectangle with specified region",
						"Successfully set rectangle with specified region", PASS);
				break;
			case setROI:
				screen.setROI(r);
				reportStepDetails("Setting rectangle with specified region",
						"Successfully set rectangle with specified region", PASS);
				break;
			case toJythonRegion:
				returnObj = Screen.toJythonRegion(r);
				reportStepDetails("Creating a duplicate region as specified region",
						"Successfully created region as specified region", PASS);
				break;
			case morphTo:
				returnObj = screen.morphTo(r);
				reportStepDetails("Moving location to specified region",
						"Successfully moved location to specified region", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, Region r, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case create:
				returnObj = Screen.create(r);
				reportStepDetails("Creating region with specified region: " + FieldName,
						"Successfully created region of " + FieldName + "with specified region", PASS);
				break;
			case setRect:
				screen.setRect(r);
				reportStepDetails("Setting rectangle with specified region " + FieldName,
						"Successfully set rectangle of " + FieldName + "with specified region", PASS);
				break;
			case setROI:
				screen.setROI(r);
				reportStepDetails("Setting rectangle with specified region " + FieldName,
						"Successfully set rectangle of " + FieldName + "with specified region", PASS);
				break;
			case toJythonRegion:
				returnObj = Screen.toJythonRegion(r);
				reportStepDetails("Creating a duplicate region as specified region " + FieldName,
						"Successfully created region of " + FieldName + " as specified region", PASS);
				break;
			case morphTo:
				returnObj = screen.morphTo(r);
				reportStepDetails("Moving location to specified region " + FieldName,
						"Successfully moved location of " + FieldName + " to specified region", PASS);
				break;
			case capture:
				returnObj = screen.capture(r);
				reportStepDetails("Capturing screen shot of  referenced image: " + FieldName,
						"Successfully captured  " + FieldName + " referenced image", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, String FieldName) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			switch (enumCommand) {

			case below:
				returnObj = screen.below();
				reportStepDetails("Getting below coordinates of referenced image: " + FieldName,
						"Successfully get below coordinates " + FieldName + " referenced image", PASS);
				break;

			case above:
				returnObj = screen.above();
				reportStepDetails("Getting above coordinates of referenced image: " + FieldName,
						"Successfully get above coordinates of " + FieldName + " referenced image", PASS);
				break;

			
			 * case getAutoWaitTimeout: returnObj = screen.getAutoWaitTimeout();
			 * reportStepDetails("Getting auto wait timeout ",
			 * "Successfully get auto wait timeout", PASS); break;
			 

			case getBottomLeft:
				returnObj = screen.getBottomLeft();
				reportStepDetails("Getting bottom left corner coordinates of referenced image: " + FieldName,
						"Successfully get bottom left corner coordinates of " + FieldName + " referenced image", PASS);
				break;
			case getBottomRight:
				returnObj = screen.getBottomRight();
				reportStepDetails("Getting bottom right corner coordinates of referenced image: " + FieldName,
						"Successfully get bottom right corner coordinates of " + FieldName + " referenced image", PASS);
				break;

			case getCenter:
				returnObj = screen.getCenter();
				reportStepDetails("Getting  center corner coordinates of referenced image: " + FieldName,
						"Successfully get center corner coordinates of " + FieldName + " referenced image", PASS);
				break;
			
			 * case getFindFailedResponse: returnObj =
			 * screen.getFindFailedResponse(); reportStepDetails(
			 * "Getting type of execution", "Successfully get type of execution"
			 * , PASS); break;
			 

			case getW:
				returnObj = screen.getW();
				reportStepDetails("Getting width of referenced image: " + FieldName,
						"Successfully get width of " + FieldName + " referenced image", PASS);
				break;
			case getH:
				returnObj = screen.getH();
				reportStepDetails("Getting height of referenced image: " + FieldName,
						"Successfully get height of " + FieldName + " referenced image", PASS);
				break;
			case getX:
				returnObj = screen.getX();
				reportStepDetails("Getting x-axis coordinates of referenced image: " + FieldName,
						"Successfully get x-axis coordinates of " + FieldName + " referenced image", PASS);
				break;

			case getY:
				returnObj = screen.getY();
				reportStepDetails("Getting y-axis coordinates of referenced image: " + FieldName,
						"Successfully get y-axis coordinates of " + FieldName + " referenced image", PASS);
				break;

			case getRect:
				returnObj = screen.getRect();
				reportStepDetails("Getting rectangle coordinates of referenced image: " + FieldName,
						"Successfully get rectangle coordinates of " + FieldName + " referenced image", PASS);
				break;
			case getROI:
				returnObj = screen.getROI();
				reportStepDetails("Getting rectangle coordinates of referenced image: " + FieldName,
						"Successfully get rectangle coordinates of " + FieldName + " referenced image", PASS);
				break;
			case getScreen:
				returnObj = screen.getScreen();
				reportStepDetails("Getting screen object of referenced image: " + FieldName,
						"Successfully get screen object of " + FieldName + " referenced image", PASS);
				break;

			case getLastMatch:
				returnObj = screen.getLastMatch();
				reportStepDetails("Getting best match of referenced image: " + FieldName,
						"Successfully get best matched Image of " + FieldName + " referenced image", PASS);
				break;

			case getLastMatches:
				returnObj = screen.getLastMatches();
				reportStepDetails("Getting best matches of referenced image: " + FieldName,
						"Successfully get best matches Image of " + FieldName + " referenced image", PASS);
				break;

			case highlight:
				screen.highlight();
				reportStepDetails("Highlighting referenced image: " + FieldName,
						"Successfully highlighted " + FieldName + " referenced image", PASS);
				break;
			
			 * case inside: returnObj = screen.inside(); reportStepDetails(
			 * "Checking smaller portion of image similarity",
			 * "Successfully checked smaller portion of image similaity", PASS);
			 * break;
			 

			case keyUp:
				screen.keyUp();
				reportStepDetails("Releasing keys specified in value: " + FieldName,
						"Successfully released " + FieldName + " keys specified in value", PASS);
				break;
			case mouseUp:
				screen.mouseUp();
				reportStepDetails("Releasing mouse button specified by value: " + FieldName,
						"Successfully release mouse " + FieldName + " button specified by value", PASS);
				break;
			case left:
				returnObj = screen.left();
				reportStepDetails("Getting left corner coordinates of referenced image: " + FieldName,
						"Successfully get left corner coordinates of " + FieldName + " referenced image", PASS);
				break;
			case right:
				returnObj = screen.right();
				reportStepDetails("Getting right corner coordinates of referenced image: " + FieldName,
						"Successfully get right corner coordinates of " + FieldName + " referenced image", PASS);
				break;
			
			 * case nearby: returnObj = screen.nearby(); reportStepDetails(
			 * "Getting nearby region of referenced image",
			 * "Successfully nearby region of referenced image", PASS); break;
			 * 
			 * case observe: screen.observe();
			 * 
			 * case listText: // returnObj=screen.listText();
			 

			case text:
				returnObj = screen.text();
				reportStepDetails("Getting text present in the referenced image: " + FieldName,
						"Successfully get text present in the " + FieldName + " referenced image", PASS);
				break;

			case getTopLeft:
				returnObj = screen.getTopLeft();
				reportStepDetails("Getting top left corner coordinates of referenced image: " + FieldName,
						"Successfully get top corner left coordinates of " + FieldName + " referenced image", PASS);
				break;

			case getTopRight:
				returnObj = screen.getTopRight();
				reportStepDetails("Getting top right corner coordinates of referenced image: " + FieldName,
						"Successfully get top corner right coordinates of " + FieldName + " referenced image", PASS);
				break;
			
			 * case getThrowException: returnObj = screen.getThrowException();
			 * reportStepDetails("Getting throw exception status",
			 * "Successfully get throw exception status", PASS); break; case
			 * stopObserver: screen.stopObserver(); reportStepDetails(
			 * "Stopping observer", "Successfully stop observer", PASS); break;
			 * case toString: returnObj = screen.toString();
			 
			case capture:
				returnObj = screen.capture();
				reportStepDetails("Capturing screen shot of  referenced image: " + FieldName,
						"Successfully captured  " + FieldName + " referenced image", PASS);
				break;
			case getBounds:
				returnObj = screen.getBounds();
				reportStepDetails("Getting bounds of referenced image: " + FieldName,
						"Successfully get bounds of " + FieldName + " referenced image", PASS);
				break;
			
			 * case getID: returnObj = screen.getID(); reportStepDetails(
			 * "Getting ID of monitor", "Successfully get ID of monitor", PASS);
			 * break; case getNumberScreens: returnObj =
			 * screen.getNumberScreens(); reportStepDetails(
			 * "Getting number of screens available",
			 * "Successfully get number of screens available", PASS); break;
			 * case getPrimaryId: returnObj = screen.getPrimaryId();
			 * reportStepDetails("Getting primaryID of current screen",
			 * "Successfully get primaryID of current screen", PASS); break;
			 * case getRobot: returnObj = screen.getRobot(); reportStepDetails(
			 * "Getting desktop robot ", "Successfully get desktop robot ",
			 * PASS); break; case initBounds: // returnObj=screen.initBounds();
			 * reportStepDetails("Getting initbounds of referenced image",
			 * "Successfully get initbounds of referenced image", PASS); break;
			 
			case selectRegion:
				if (FieldName.charAt(0) == '$') {
					returnObj = screen.selectRegion();
					reportStepDetails(
							"selecting region of referenced image at runtime: "
									+ FieldName.substring(1, FieldName.length()),
							"Successfully select region of " + FieldName.substring(1, FieldName.length())
									+ " referenced image at runtime",
							PASS);
				} else {
					returnObj = screen.selectRegion(FieldName);
					reportStepDetails("selecting region of referenced image at runtime",
							"Successfully select region of  referenced image at runtime", PASS);
				}

				break;
			case userCapture:
				if (FieldName.charAt(0) == '$') {
					returnObj = screen.userCapture();
					reportStepDetails(
							"Capturing user specified image at runtime: " + FieldName.substring(1, FieldName.length()),
							"Successfully captured user specified " + FieldName.substring(1, FieldName.length())
									+ " mage at runtime",
							PASS);

				} else {
					returnObj = screen.userCapture(FieldName);
					reportStepDetails("Capturing user specified image at runtime: ",
							"Successfully captured user specified  image at runtime", PASS);
				}

				break;

			case getGraphicsDevice:
				returnObj = screen.getGraphicsDevice();
				reportStepDetails("Getting graphics device of referenced image" + FieldName,
						"Successfully get graphic device of " + FieldName + " referenced image", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, String target, double delaytime, double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);
			switch (enumCommand) {

			case dropAt:
				// returnObj = screen.dropAt(ImagePath, delaytime);
				returnObj = screen.dropAt(new Pattern(ImagePath).similar((float) similarityValue), delaytime);

				reportStepDetails("Dropping  Button/Image ", "Successfully droped the Button/Image of a field  ", PASS);
				break;
			case exists:
				// returnObj = screen.exists(ImagePath, delaytime);
				returnObj = screen.exists(new Pattern(ImagePath).similar((float) similarityValue), delaytime);

				reportStepDetails("Checking  Button/Image existence  ",
						"Successfully checked the Button/Image of a field exists ", PASS);
				break;
			case wait:
				// returnObj = screen.wait(ImagePath, delaytime);
				returnObj = screen.wait(new Pattern(ImagePath).similar((float) similarityValue), delaytime);

				reportStepDetails("Checking process waits until Button/Image of field appears",
						"Successfully waits process until Button/Image of field appears", PASS);
				break;
			case waitVanish:
				// returnObj = screen.waitVanish(ImagePath, delaytime);
				returnObj = screen.waitVanish(new Pattern(ImagePath).similar((float) similarityValue), delaytime);

				reportStepDetails("Checking process waits until Button/Image of field vanish",
						"Successfully waits process until Button/Image of field vanish", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, String target, double delaytime, String FieldName, double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);
			switch (enumCommand) {

			case dropAt:
				// returnObj = screen.dropAt(ImagePath, delaytime);
				returnObj = screen.dropAt(new Pattern(ImagePath).similar((float) similarityValue), delaytime);

				reportStepDetails("Dropping  Button/Image: " + FieldName,
						"Successfully droped " + FieldName + " the Button/Image of a field  ", PASS);
				break;
			case exists:
				// returnObj = screen.exists(ImagePath, delaytime);
				returnObj = screen.exists(new Pattern(ImagePath).similar((float) similarityValue), delaytime);
				captureScreenShot();
				reportStepDtlsWithScreenshot("Checking button/link/image existence:" + FieldName,
						FieldName + " exists on page", PASS);
				break;
			case wait:
				// returnObj = screen.wait(ImagePath, delaytime);
				returnObj = screen.wait(new Pattern(ImagePath).similar((float) similarityValue), delaytime);

				reportStepDetails("Checking process waits until Button/Image of field appears",
						"Successfully waits process until Button/Image of field appears", PASS);
				break;

			case waitVanish:
				// returnObj = screen.waitVanish(ImagePath, delaytime);
				returnObj = screen.waitVanish(new Pattern(ImagePath).similar((float) similarityValue), delaytime);

				reportStepDetails("Checking process waits until Button/Image of field vanish",
						"Successfully waits process until Button/Image of field vanish", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, String target, int modifier, double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);
			switch (enumCommand) {

			case click:
				// returnObj = screen.click(ImagePath, modifier);
				returnObj = screen.click(new Pattern(ImagePath).similar((float) similarityValue), modifier);

				reportStepDetails("Clicking on Button/Image of field", "Successfully clicked on Button/Image of field",
						PASS);
				break;
			case clickAndWait:
				returnObj = screen.click(new Pattern(ImagePath).similar((float) similarityValue), modifier);
				synchronized (screen) {
					screen.wait(30);
				}
				captureScreenShot();
				reportStepDtlsWithScreenshot("Clicking Button/Image:",
						"Successfully clicked the Button/Image of a field  ", PASS);

				break;

			case doubleClick:
				// returnObj = screen.doubleClick(ImagePath, modifier);
				returnObj = screen.doubleClick(new Pattern(ImagePath).similar((float) similarityValue), modifier);

				reportStepDetails("Double Clicking on Button/Image of field",
						"Successfully double clicked on Button/Image of field", PASS);
				break;
			case rightClick:
				// returnObj = screen.rightClick(ImagePath, modifier);
				returnObj = screen.rightClick(new Pattern(ImagePath).similar((float) similarityValue), modifier);

				reportStepDetails("Clicking mouse right button on Button/Image of field",
						"Successfully clicked mouse right Button on Button/Image of field", PASS);
				break;
			case type:
				// target is the text
				String value = getExcelData(target);
				returnObj = screen.type(value, modifier);
				reportStepDetails("Setting value to target", "Successfully  set " + value + " value to target", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, String target, int modifier, String FieldName, double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);
			switch (enumCommand) {

			case click:
				// returnObj = screen.click(ImagePath, modifier);
				returnObj = screen.click(new Pattern(ImagePath).similar((float) similarityValue), modifier);

				reportStepDetails("Clicking on Button/Image of field: " + FieldName,
						"Successfully clicked " + FieldName + " on Button/Image of field", PASS);
				break;
			case clickAndWait:
				returnObj = screen.click(new Pattern(ImagePath).similar((float) similarityValue), modifier);
				synchronized (screen) {
					screen.wait(30);
				}
				captureScreenShot();
				reportStepDtlsWithScreenshot("Clicking Button/Image:" + FieldName,
						"Successfully clicked" + FieldName + " the Button/Image of a field  ", PASS);
				break;

			case doubleClick:
				System.out.println("Nandi" + modifier);
				// returnObj = screen.doubleClick(ImagePath, modifier);
				returnObj = screen.doubleClick(new Pattern(ImagePath).similar((float) similarityValue), modifier);

				reportStepDetails("Double Clicking on Button/Image of field: " + FieldName,
						"Successfully double clicked on " + FieldName + " Button/Image of field", PASS);
				break;
			case rightClick:
				// returnObj = screen.rightClick(ImagePath, modifier);
				returnObj = screen.rightClick(new Pattern(ImagePath).similar((float) similarityValue), modifier);

				reportStepDetails("Clicking mouse right button on Button/Image of field: " + FieldName,
						"Successfully clicked mouse right Button on " + FieldName + "Button/Image of field", PASS);
				break;
			case type:
				String value = getExcelData(target);
				returnObj = screen.type(value, modifier);

				reportStepDetails("Setting value to target: " + FieldName,
						"Successfully  set " + value + " value to target", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, String target, String FieldName, double similarityValue) {
		System.out.println("Enter ed");
		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);
			String desImagePath = GetImagePath(FieldName);
			System.out.println("ImagePath" + ImagePath);
			switch (enumCommand) {

			case click:
				// returnObj = screen.click(ImagePath);
				returnObj = screen.click(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Clicking button/link/image:" + FieldName,
						"Successfully clicked button/link/image:" + FieldName, PASS);
				break;

			case clickAndWait:
				returnObj = screen.click(new Pattern(ImagePath).similar((float) similarityValue));
				synchronized (screen) {
					screen.wait(30);
				}
				captureScreenShot();
				reportStepDtlsWithScreenshot("Clicking Button/Image:" + FieldName,
						"Successfully clicked" + FieldName + " the Button/Image of a field  ", PASS);
				break;
			case dragDrop:
				// returnObj = screen.dragDrop(SourceImagePath, desImagePath,
				// modifiers);
				returnObj = screen.dragDrop(new Pattern(ImagePath).similar((float) similarityValue),
						new Pattern(desImagePath).similar((float) similarityValue));
				reportStepDetails("Dragging and dropping image from source to destination: " + FieldName,
						"Successfully drag and drop image " + FieldName + " from source to destination", PASS);
				break;

			case doubleClick:
				// returnObj = screen.doubleClick(ImagePath);
				returnObj = screen.doubleClick(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Clicking Button/Image:" + FieldName,
						"Successfully double clicked" + FieldName + " the Button/Image of a field  ", PASS);
				break;

			case drag:
				// Pattern pat = new Pattern(ImagePath);

				// returnObj = screen.drag(ImagePath);
				returnObj = screen.drag(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Dragging  Button/Image", "Successfully dragged  the Button/Image of a field  ",
						PASS);
				break;

			case dropAt:
				// returnObj = screen.dropAt(ImagePath);
				returnObj = screen.dropAt(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Dropping  Button/Image ", "Successfully droped the Button/Image of a field  ", PASS);
				break;

			case exists:

				// returnObj = screen.exists(ImagePath);
				returnObj = screen.exists(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Checking  Button/Image existence  ",
						"Successfully checked the Button/Image of a field exists ", PASS);
				break;

			case find:
				// returnObj = screen.find(ImagePath);
				returnObj = screen.find(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;

			case findAll:
				// returnObj = screen.findAll(ImagePath);
				returnObj = screen.findAll(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;

			case findAllNow:
				// returnObj = screen.findAllNow(ImagePath);
				returnObj = screen.findAllNow(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Finding correct image match of the  given pattern on the screen ",
						"Successfully find correct image match on the screen", PASS);
				break;

			case getLocationFromPSRML:
				returnObj = screen.getLocationFromPSRML(new Pattern(ImagePath).similar((float) similarityValue));
				// returnObj = screen.getLocationFromPSRML(ImagePath);
				reportStepDetails("Finding given target where it is located", "Successfully find target location",
						PASS);
				break;

			case getRegionFromPSRM:
				// returnObj = screen.getRegionFromPSRM(ImagePath);
				returnObj = screen.getRegionFromPSRM(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Finding given target where it is located in region",
						"Successfully find target location in region", PASS);
				break;

			case hover:
				// returnObj = screen.hover(ImagePath);
				returnObj = screen.hover(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Checking mouse cursor placed on given target",
						"Successfully checked mouse cursor placed on given target", PASS);
				break;

			case mouseMove:
				// returnObj = screen.mouseMove(ImagePath);
				returnObj = screen.mouseMove(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Checking mouse moved on given target",
						"Successfully checked mouse moved  on given target", PASS);
				break;

			case rightClick:
				// returnObj = screen.rightClick(ImagePath);
				returnObj = screen.rightClick(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Clicking mouse right button on Button/Image of field",
						"Successfully clicked mouse right button on Button/Image of field", PASS);
				break;

			case wait:
				// returnObj = screen.wait(ImagePath);
				returnObj = screen.wait(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Checking process waits until Button/Image of field appears",
						"Successfully waits process until Button/Image of field appears", PASS);
				break;

			case waitVanish:
				// returnObj = screen.waitVanish(ImagePath);
				returnObj = screen.waitVanish(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Checking process waits until Button/Image of field vanish",
						"Successfully waits process until Button/Image of field vanish", PASS);
				break;

			case type:
				String value = getExcelData(target);
				returnObj = screen.type(value);

				reportStepDetails("Setting value to target: " + FieldName,
						"Successfully  set value " + value + " to " + FieldName + "", PASS);
				break;

			case paste:
				String value1 = getExcelData(target);
				returnObj = screen.paste(value1);

				reportStepDetails("Setting value to target: " + FieldName,
						"Successfully  set value " + target + " to " + FieldName + "", PASS);
				break;

			case keyDown:
				screen.keyDown(target);
				reportStepDetails("Pressing keydown specified in target" + FieldName,
						"Successfully press down the key " + FieldName + " specified in target", PASS);
				break;
			case keyUp:
				screen.keyUp(target);
				reportStepDetails("Releasing keys specified by target" + FieldName,
						"Successfully released key" + FieldName + "specified by target", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, String target, double similarityValue) {
		System.out.println("Enter ed");
		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String ImagePath = GetImagePath(target);
			System.out.println("ImagePath" + ImagePath);
			switch (enumCommand) {
			case hover:
				// returnObj = screen.hover(ImagePath);
				returnObj = screen.hover(new Pattern(ImagePath).similar((float) similarityValue));
				reportStepDetails("Checking mouse cursor placed on given target",
						"Successfully checked mouse cursor placed on given target", PASS);
				break;
			case click:
				// returnObj = screen.click(ImagePath);
				returnObj = screen.click(new Pattern(ImagePath).similar((float) similarityValue));

				reportStepDetails("Clicking Button/Image:", "Successfully clicked the Button/Image of a field  ", PASS);
				break;
			case exists:
				returnObj = screen.exists(new Pattern(ImagePath).similar((float) similarityValue));
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, String sourcetarget, String obj, int modifiers, double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(sourcetarget);
			String desImagePath = GetImagePath(obj);
			switch (enumCommand) {

			case dragDrop:
				// obj is the destination
				returnObj = screen.dragDrop(new Pattern(SourceImagePath).similar((float) similarityValue),
						new Pattern(desImagePath).similar((float) similarityValue), modifiers);

				// returnObj = screen.dragDrop(SourceImagePath, desImagePath,
				// modifiers);
				reportStepDetails("Dragging and dropping image from source to destination",
						"Successfully drag and drop image from source to destination", PASS);
				break;

			case type:
				// obj is the text
				String value = getExcelData(obj);
				returnObj = screen.type(new Pattern(SourceImagePath).similar((float) similarityValue), value,
						modifiers);

				// returnObj = screen.type(SourceImagePath, value, modifiers);
				reportStepDetails("Setting value to target", "Successfully  set " + value + " value to target", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, String sourcetarget, String obj, int modifiers, String FieldName,
			double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(sourcetarget);
			String desImagePath = GetImagePath(obj);
			switch (enumCommand) {

			case dragDrop:
				// returnObj = screen.dragDrop(SourceImagePath, desImagePath,
				// modifiers);
				returnObj = screen.dragDrop(new Pattern(SourceImagePath).similar((float) similarityValue),
						new Pattern(desImagePath).similar((float) similarityValue), modifiers);
				reportStepDetails("Dragging and dropping image from source to destination: " + FieldName,
						"Successfully drag and drop image " + FieldName + " from source to destination", PASS);
				break;

			case type:
				// obj is the text
				String value = getExcelData(obj);
				// returnObj = screen.type(SourceImagePath, value, modifiers);
				returnObj = screen.type(new Pattern(SourceImagePath).similar((float) similarityValue), value,
						modifiers);
				reportStepDetails("Setting value to target: " + FieldName,
						"Successfully  set value " + value + " to target", PASS);
				break;

			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, String sourcetarget, String destinationtarget) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(sourcetarget);
			String desImagePath = GetImagePath(destinationtarget);
			switch (enumCommand) {

			case dragDrop:
				returnObj = screen.dragDrop(SourceImagePath, desImagePath);
				reportStepDetails("Dragging and dropping image from source to destination",
						"Successfully drag and drop image from source to destination", PASS);
				break;

			case type:
				returnObj = screen.type(SourceImagePath, desImagePath);
				reportStepDetails("Setting value to target", "Successfully  set value to target", PASS);
				break;
			case paste:
				returnObj = screen.paste(SourceImagePath, desImagePath);
				reportStepDetails("Setting value to target", "Successfully  set value to target", PASS);
				break;
			case selectRegion:
				returnObj = screen.selectRegion(sourcetarget);
				reportStepDetails("selecting region of referenced image at runtime:" + destinationtarget,
						"Successfully select region of  referenced " + destinationtarget + " image at runtime", PASS);
			case userCapture:
				returnObj = screen.userCapture(sourcetarget);
				reportStepDetails("Capturing user specified image at runtime" + destinationtarget,
						"Successfully captured user specified " + destinationtarget + " image at runtime", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);

			}
		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public Object effecta(String command, String sourcetarget, String obj, String FieldName, double similarityValue) {

		Object returnObj = null;
		try {
			enumCommand = checkEnumConstant(command);
			String SourceImagePath = GetImagePath(sourcetarget);
			String desImagePath = GetImagePath(obj);
			switch (enumCommand) {

			case dragDrop:
				// obj is the destination
				// returnObj = screen.dragDrop(SourceImagePath, desImagePath);
				returnObj = screen.dragDrop(new Pattern(SourceImagePath).similar((float) similarityValue),
						new Pattern(desImagePath).similar((float) similarityValue));

				reportStepDetails("Dragging and dropping image from source to destination: " + FieldName,
						"Successfully drag and drop image " + FieldName + " from source to destination", PASS);
				break;

			case type:
				// obj is the text
				String value = getExcelData(obj);
				// returnObj = screen.type(SourceImagePath, value);
				returnObj = screen.type(new Pattern(SourceImagePath).similar((float) similarityValue), value);

				reportStepDetails("Setting value to target " + FieldName,
						"Successfully  set value  " + value + " to target ", PASS);
				break;
			case paste:
				String value1 = getExcelData(obj);
				// returnObj = screen.paste(SourceImagePath, value1);
				returnObj = screen.paste(new Pattern(SourceImagePath).similar((float) similarityValue), value1);

				reportStepDetails("Setting value to target " + FieldName,
						"Successfully  set value  " + FieldName + " to target", PASS);
				break;
			default:
				reportUnknownSikuliCmdErr(command);
			}

		} catch (Exception e) {
			reportException(command, e.toString());
		}
		return returnObj;
	}

	public String GetImagePath(String target) {
		String ImagePath = sImagePath + target + sImageExt;
		System.out.println(sImagePath);
		return ImagePath;
	}

	public void reportException(String sCommand, String sErrMsg) {
		reportStepDetails(sCommand, "Thrown an error : " + encodeSpecialCharacters(sErrMsg), FAIL);
		stopSikuli();
	}

	public void reportUnknownSikuliCmdErr(String sCommand) {
		reportStepDetails("Unknown Sikuli/effecta Command " + "\"" + sCommand + "\"", "Please Contact Automtaion team",
				FAIL);
		stopSikuli();
	}

	public void stopSikuli() {
		try {
			resultXMLFileCreation();
			System.exit(0);
		} catch (Exception e) {
			System.out.println(" in stopSelenium method: Hurrah you did mistake : " + e);
			System.exit(0);
		}
	}

	public void captureScreenShot() {
		try {

			BufferedImage bImage = screen.capture().getImage();
			String ImagePath = sResultsFolderPath + "Screenshots" + File.separator + sECName + "_"
					+ getCurrentDateNTime() + "_" + iStepCount + ".png";
			ImageIO.write(bImage, ".PNG", new File(ImagePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	*//**
	 * This method captures screenshot and adds step details with screenshot to
	 * array lists used for reporting
	 * 
	 * @param ver
	 *            Verification
	 * @param desc
	 *            Description
	 * @param status
	 *            Pass (or) Fail status
	 *//*
	public void reportStepDtlsWithScreenshot(String ver, String des, String stepStatus) {
		captureScreenShot();
		verification.add(ver);

		
		 * description.add(des + "Screenshot:" + sAutomationPath +"Results"
		 * +File.separator+ "Screenshots" + File.separator + sECName + "_" +
		 * getCurrentDateNTime() + "_" + iStepCount + ".png");
		 
		description.add(des + "Screenshot:" + sAutomationPath + "Results" + File.separator + "Screenshots"
				+ File.separator + sECName + iStepCount + ".png");

		System.out.println("Report step details method path:" + des + "Screenshot:" + sAutomationPath + "Results"
				+ File.separator + "Screenshots" + File.separator + sECName + "_" + getCurrentDateNTime() + "_"
				+ iStepCount + ".png");
		status.add(stepStatus);
		dateTime.add(getExecutionTime());
		if (stepStatus.equalsIgnoreCase(FAIL))
			sResultFlag = stepStatus;
		iStepCount += 1;
	}

*/}
