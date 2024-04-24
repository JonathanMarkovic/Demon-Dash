import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

public class Scroll extends World
{
    All all; // an object to record an infinite side-scrolling world and help maintain an unchanging world layout
    int scrollX, maxLeft, maxRight; // all three of these are needed to control side-scrolling

    /**
     * Scroll Constructor: creates the world object and instantiates the layout recorder (a list of objects that each contain an 
     *                     actor and an x and y coordinate for that actor; sorted by their x-coordinates)
     *
     * @param w: the width of the new world
     * @param h: the height of the new world
     * @param c: the cellsize of the new world
     */
    public Scroll(int w, int h, int c)
    {    
        super(w, h, c); // creates the new world
        all = new All(w); // instantiate the world layout recorder
    }

    /**
     * Method scroll: call this method to side-scroll the world; a positive value for 'dir' moves all scrollable
     *                objects to the left and a negative value moves them to the right;
     *
     * @param dir: from the center of the world view screen, the horizontal offset of the point that is to be moved to the center;
     *             or, the horizontal offset that the main actor has moved from the center; used to move all scrollables so that
     *             the main actor is back in the center.
     */
    public void scroll(int dir)
    {
        // shift all actors against scroll direction
        for (Object obj : getObjects(null))
        {
            Actor actor = (Actor) obj;
            actor.setLocation(actor.getX()-dir, actor.getY());
        }
        // determine new scroll value
        scrollX += dir;
        // the following three statements adds all actors coming into view into the world and
        // removes all actors going out of view from the world (out of bounds of viewpoint of character)
        Combo combo = all.scroll(scrollX);
        for (Any any : combo.removals) removeObject((Actor)any.object); // removes actors listed to be removed from the world
        for (Any any : combo.adds) addObject((Actor)any.object, any.objectX-scrollX, any.objectY); // adds actors listed to be added to the world
    }

    
    /**
     * Method addObj: Use this method to add a scrollable object into the world.
     *                It records each actor with its real world location as a container object in a list of scrollables;
     *                then, adds the object into the world.
     *
     * @param actor: the scrollable actor that is to be added to the world; the object is recorded in a list of scrollables
     * @param x: the x coordinate for the actor to be placed in the world at and saved with the actor in the list
     * @param y: the y coordinate for the actor to be placed in the world at and saved with the actor in the list
     */
    public void addObj(Actor actor, int x, int y)
    {
        all.add(new Any(actor, x+scrollX, y)); // adds the new 'Any' object to the list of scrollables
        addObject(actor, x, y); // adds the object into the world
    }

    /**
     * Method moveObj: Use this method to 'move' a scrollable object.
     *                 It adjusts the coordinates of its listed container object; then, moves the actor.
     *
     * @param actor: the scrollable actor that is to move
     * @param dx: the offset along the x-axis that the actor is to move
     * @param dy: the offset along the y-axis that the actor is to move
     */
    public void moveObj(Actor actor, int dx, int dy)
    {
        all.move(actor, dx, dy); // adjusts the coordinates in the 'Any' object of this actor
        actor.setLocation(actor.getX()+dx, actor.getY()+dy); // moves the actor
    }

    /**
     * Method removeObj: Use this method to remove a scrollable object from the world.
     *                   It removes the container object of the given actor from the list of scrollable objects;
     *                   then, removes the actor from the world.
     *
     * @param actor: the actor that is to be removed from the world and whose container object is to be removed from the list
     */
    public void removeObj(Actor actor)
    {
        all.remove(actor); // removes the 'Any' object of this object from the list of scrollable objects
        removeObject(actor); // removes the actor from the world
    }

    /**
     * Class All:  a class to help control the adding and removing of objects in a bounded world that
     *             acts like an infinitely unbounded unchanging side-scrolling world; an array
     *             of 'Any' objects is to hold all scrollable actors and their locations in
     *             the scrollable world; the 'scroll' method is used to retrieve a 'Combo' object which
     *             contains two lists; the first lists the objects coming into view (to be added back
     *             into the world) and the other lists the objects going out of view (to be removed from
     *             the world), as well as move those object that continue to exist in the world.  Use
     *             the 'add' method to include an 'Any' object to the list of scrollables.
     */
    private class All
    {
        ArrayList<Any> all = new ArrayList<Any>(); // to hold all scrollables
        int worldWidth, firstRight, firstLeft, lastHomeX;

        /**
         * All Constructor: saves the width of the world; this value is needed to determine which
         *                  objects are leaving and returning into view on the right side of view screen
         *
         * @param width: the width of the world
         */
        public All(int width)
        {
            worldWidth = width;
        }

        /**
         * Method scroll: called by the 'scroll' method of the Scroll class; this method
         *                returns an object containing two lists of 'Any' objects;
         *                one list contains scrollables to be added to the world;
         *                the other list contains scrollables to be removed from the world
         *
         * @param newHomeX: the new scroll value
         * @return: a 'Combo' object containing two lists of 'Any' objects
         */
        public Combo scroll(int newHomeX)
        {
            
            ArrayList<Any> addList = new ArrayList<Any>();
            ArrayList<Any> removeList = new ArrayList<Any>();
            if (newHomeX < lastHomeX)
            { // scrolling left
                while (firstLeft > 0 && all.get(firstLeft - 1).objectX >= newHomeX)
                { // object coming into view
                    addList.add(all.get(firstLeft - 1)); // adds the 'Any' object of an object coming into view to the add list
                    firstLeft--; // adjusts the left pointer to compensate for the 'Any' object being considered now in view
                }
                while (firstRight > 0 && all.get(firstRight - 1).objectX >= newHomeX + worldWidth)
                { // object going out of view
                    removeList.add(all.get(firstRight - 1)); // adds the 'Any' object of an object going out of view to the remove list
                    firstRight--; // adjusts the right pointer to compensate for the 'Any' object being considered now out of view
                }
            }
            else
            { // scrolling right
                while (firstRight < all.size() && all.get(firstRight).objectX < newHomeX + worldWidth)
                { // object coing into view
                    addList.add(all.get(firstRight)); // adds the 'Any' object of an object coming into view to the add list
                    firstRight++; // adjusts the right pointer to compensate for the 'Any' object being considered now in view
                }
                while (firstLeft < all.size() && all.get(firstLeft).objectX < newHomeX)
                { // object going out of view
                    removeList.add(all.get(firstLeft)); // adds the 'Any' object of an object going out of view to the remove list
                    firstLeft++; // adjusts the left pointer to compensate for the 'Any' object being considered now out of view
                }
            }
            lastHomeX = newHomeX; // saves the new scroll amount
            return new Combo(addList, removeList); // returns both the add list and the remove list in a Combo object
        }

        /**
         * Method add: adds a new 'Any' object to the list of scrollables;
         *             the list is sorted in ascending order by the x coordinate of the object in the world
         *
         * @param any: a container object consisting of the object and its x and y coordinates in the world
         */
        public void add(Any any)
        {
            int index = 0;
            while (index < all.size() && any.objectX > all.get(index).objectX) index++; // find insertion point
            all.add(index, any); // add the 'Any' object to the list at insertion point
            firstRight++; // adjusts the right pointer to compensate for the added item on the list
        }

        /**
         * Method remove: removes an 'Any' object containing the given object from the list of scrollables
         *
         * @param obj:  the object used to determine which 'Any' object is removed
         */
        public void remove(Object obj)
        {
            int index = firstLeft;
            while (index < firstRight && !all.get(index).object.equals(obj)) index++; // find object in list
            if (index == firstRight)
            { // object not found to be in view -- show message and exit method
                System.out.println("'remove' in All class could not find 'obj'");
                return;
            }
            all.remove(index); // removes the 'Any' object containing the given scrollable object from the list
            firstRight--; // adjusts the right pointer to compensate for the item being removed from the list
        }

        /**
         * Method changeLocation: sets new x and y coordinates for the 'Any' object containing the given object;
         *                        the 'Any' object is removed and re-inserted into the list to maintain sort order
         *
         * @param obj: the object used to determine which 'Any' object is to be worked with
         * @param newX: the new x coordinate for the 'Any' object containing the given object 
         * @param newY: the new y coordinate for the 'Any' object containing the given object
         */
        public void changeLocation(Object obj, int newX, int newY)
        {
            int index = firstLeft;
            while (index < firstRight && !all.get(index).object.equals(obj)) index++; // find object in list
            if (index == firstRight)
            { // object not found to be in view -- show message and exit method
                System.out.println("'changeLocation' in All class could not find 'obj'");
                return;
            }
            Any any = all.remove(index); // removes the 'Any' object containing the given object from the list
            firstRight--; // adjusts the right pointer to compensate for the item being removed from the list
            any.changeLocation(newX, newY); // sets new coordinates for the 'Any' object removed from the list
            add(any); // adds the 'Any' object removed back into the list of scrollable objects
        }

        /**
         * Method move: adjusts the x and y coordinate for the 'Any' object containing the given object;
         *              the 'Any' object is removed and re-inserted into the list to maintain sort order
         *
         * @param obj: the object used to determine which 'Any' object is to be worked with
         * @param dx: the adjustment value of the x coordiante for the 'Any' object containing the given object
         * @param dy: the adjustment value of the y coordinate for the 'Any' object containing the given object
         */
        public void move(Object obj, int dx, int dy)
        {
            int index = firstLeft;
            while (index < firstRight && !all.get(index).object.equals(obj)) index++; // find object in list
            if (index == firstRight)
            { // object not found to be in view -- show message and exit method
                System.out.println("'move' in All class could not fine 'obj'");
                return;
            }
            Any any = all.remove(index); // removes the 'Any' object containing the given object from the list
            firstRight--; // adjusts the right pointer to compensate for the item being removed from the list
            any.move(dx, dy); // adjusts the coordinates for the 'Any' object removed from the list
            add(any); // adds the 'Any' object removed back into the list of scrollable objects
        }
    }
    
    /**
     * Class Any:  a simple class whose instances each hold an actor and the real x and y coordinates of that
     *             actor in a world; used as a container object to keep an actor and its location together.
     */
    private class Any
    {
        Object object = null; // any scrollable object
        int objectX, objectY; // the real x and y coordinates in the real world for the scrollable object
        
        /**
         * Any Constructor: combines the given scrollable object and the given coordinates into one 'Any' object
         *
         * @param obj the scrollable object
         * @param x the x coordinate of the object in the real world
         * @param y the y coordinate of the object in the real world
         */
        public Any(Object obj, int x, int y)
        {
            object = obj; // saves the scrollable object
            objectX = x; // saves the real x coordinate in the real world for the scrollable object
            objectY = y; // saves the real y coordinate in the real world for the scrollable object
        }
        
        /**
         * Method changeLocation: sets new real x and y coordinates of an 'Any' object
         *
         * @param newX the new real x coordinate of the object of this 'Any' object in the real world
         * @param newY the new real y coordinate of the object of this 'Any' object in the real world
         */
        public void changeLocation(int newX, int newY)
        {
            objectX = newX; // sets the new x coordinate in the real world of the scrollable object of this 'Any' object
            objectY = newY; // sets the new y coordinate in the real world of the scrollable object of this 'Any' object
        }
        
        /**
         * Method move: adjusts the real x and y coordinates of an 'Any' object
         *
         * @param dx the adjustment amount for the x coordinate of the object of this 'Any' object in the real world
         * @param dy the adjustment amount for the y coordinate of the object of this 'Any' object in the real world
         */
        public void move(int dx, int dy)
        {
            objectX += dx; // adjusts the x coordinate in the real world of the scrollable object of this 'Any' object
            objectY += dy; // adjusts the y coordinate in the real world of the scrollable object of this 'Any' object
        }
    }
    
    /**
     * Class Combo:  a simple class whose instances each hold two lists of 'Any' objects;
     *               used as a container object, allowing two lists to be passed as one object.
     */
    private class Combo
    {
        ArrayList<Any> adds = new ArrayList<Any>();
        ArrayList<Any> removals = new ArrayList<Any>();
    
        /**
         * Combo Constructor: combines the given two lists within a new Combo object
         *
         * @param adding A parameter
         * @param removing A parameter
         */
        public Combo(ArrayList<Any> adding, ArrayList<Any> removing)
        {
            adds = adding;
            removals = removing;
        }
    }
}
