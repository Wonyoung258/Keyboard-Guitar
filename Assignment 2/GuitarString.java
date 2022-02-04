// Wonyoung Kim
// CSE 143
// Section AR
// Assignment #2: GuitarHero

import java.util.*;

// Class GuitarString can be used to create a guitar string
public class GuitarString {

   private Queue<Double> ringBuffer;   // Keeps track of the ring buffer
   private int cap;     // capacity of the ring buffer

   private static final double DECAY_FACTOR = 0.996;
   
   // pre: frequency = given double. 
   //       frequency <= 0 or size of resulting ring buffer < 2 
   //       (throws IllegalArgumentException if not)
   // post: Constructs a guitar string of the given frequency
   public GuitarString(double frequency) {
      cap = (int)Math.round((StdAudio.SAMPLE_RATE / frequency));
      if (frequency <= 0 || cap < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for (int i = 0; i < cap; i++) {
         ringBuffer.add(0.0);
      }
   }
   
   // pre: init size >= 2 (throws IllegalArgumentException if not)
   // can test the GuitarString and ring buffer 
   public GuitarString(double[] init) {
      cap = init.length;
      if (cap < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for (Double num : init) {
         ringBuffer.add(num);
      }
   }
   
   // post: replace N elements with N random values. -0.5 <= value < 0.5
   public void pluck() {
      Random ran = new Random();
      for (int i = 0; i < cap; i++) {
         ringBuffer.remove();
         ringBuffer.add(ran.nextDouble() -0.5);
      }
   }
   
   
   // post: Applies Karplus-Strong update once to the ring buffer
   public void tic() {
      double value1 = ringBuffer.remove();
      double value2 = ringBuffer.peek();
      ringBuffer.add((value1 + value2) / 2 * DECAY_FACTOR);
   }
   
   // post: returns current sample
   public double sample() {
      return ringBuffer.peek();
   }
}