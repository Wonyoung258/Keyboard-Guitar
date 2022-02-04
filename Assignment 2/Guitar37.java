// Wonyoung Kim
// CSE 143
// Section AR
// Assignment #2: GuitarHero

// Guitar 37 can be used to make a guitar with 37 strings. 110Hz to 880Hz
public class Guitar37 implements Guitar {
    public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    public static final int LENGTH = KEYBOARD.length();
    
    private GuitarString[] keys;    // Array to keep track of keys/pitch
    private int ticTimes = 0;    // keeps track of the times tic is called
   
   // post: creates an array GuitarString with 37 notes
   public Guitar37() {
      keys = new GuitarString[LENGTH];
      
      for (int i = 0; i < LENGTH; i++) {
         keys[i] = new GuitarString(440.0 * Math.pow(2, ((i - 24.0) / 12.0)));
      }
   }
   
   // pre: pitch = given pitch that is desired
   // post: plays the note with the given pitch. 
   //       Concert A = pitch 0
   //       If the pitch can't be played, it is ignored
   public void playNote(int pitch) {
      int index = pitch + 24;
      if (index < LENGTH && index >= 0) {
         keys[index].pluck();
      }
   }
   
   // pre: key = character on the keyboard typed
   // post: returns whether the char exists among the KEYBOARD
   public boolean hasString(char key) {
      return (KEYBOARD.indexOf(key) != -1);
   }
   
   // pre: key = letter character on KEYBOARD (throws IllegalArgumentException if not)
   // post: plucks the string corresponding to the character
   public void pluck(char key) {
      if (!hasString(key)) {
         throw new IllegalArgumentException();
      }
      
      keys[KEYBOARD.indexOf(key)].pluck();
   }
   
   // post: returns sum of all samples from the strings
   public double sample() {
      double sum = 0.0;
      
      for (GuitarString keys : keys) {
         sum += keys.sample();
      }
      
      return sum;
   }
   
   // post: advances the time forward one tic
   public void tic() {
      for (GuitarString keys : keys) {
         keys.tic();
      }
      ticTimes++;
   }
   
   // post: returns number of times tic has been called
   public int time() {
      return ticTimes;
   }
}