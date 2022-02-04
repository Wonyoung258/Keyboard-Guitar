// skeleton version of the class

public class Guitar37 implements Guitar {
    public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    public static final int LENGTH = KEYBOARD.length();
    
    private GuitarString[] keys;
    private int ticTimes = 0;
   
   public Guitar37() {
      keys = new GuitarString[LENGTH];
      
      for (int i = 0; i < LENGTH; i++) {
         keys[i] = new GuitarString(440.0 * Math.pow(2, ((i - 24.0) / 12.0)));
      }
   }
   
   public void playNote(int pitch) {
      int index = pitch + 24;
      if (index < LENGTH && index >= 0) {
         keys[index].pluck();
      }
   }
   
   public boolean hasString(char key) {
      return (KEYBOARD.indexOf(key) != -1);
   }
   
   public void pluck(char key) {
      if (!hasString(key)) {
         throw new IllegalArgumentException();
      }
      
      keys[KEYBOARD.indexOf(key)].pluck();
   }
   
   public double sample() {
      double sum = 0.0;
      
      for (GuitarString keys : keys) {
         sum += keys.sample();
      }
      
      return sum;
   }
   
   public void tic() {
      for (GuitarString keys : keys) {
         keys.tic();
      }
      ticTimes++;
   }
   
   public int time() {
      return ticTimes;
   }
}