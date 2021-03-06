/*
 * Copyright (C) 2014, Markus Sprunck <sprunck.markus@gmail.com>
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * - The name of its contributor may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package v1;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import tests.Parameter;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BandMatrixTest {

   static v1.BandMatrix A;

   static v1.Matrix B;

   @BeforeClass
   public static void v1_setup() {

      tests.TestRunner.setupTestData();

      A = new v1.BandMatrix(Parameter.ROW_NUMBER, Parameter.BAND_WIDTH);
      B = new v1.Matrix(Parameter.ROW_NUMBER, 1);
      for (int row = 0; row < Parameter.ROW_NUMBER; row++) {
         for (int col = row; col < row + Parameter.BAND_WIDTH && col < Parameter.ROW_NUMBER; col++) {
            A.setValue(row, col, tests.TestRunner.A.getValue(row, col));
         }
         B.setValue(row, 0, tests.TestRunner.B.getValue(row));
      }
   }

   @Test
   public void v1_times_5x5_SymetricBandMatrix_CorrectResult() {

      // ARRANGE
      final int rowsNumber = 5;
      final int bandwidth = 3;

      /*
       * |  10   11    12    -    - |
       * |  11   13    14   15    - |
       * |  12   14    16   17   18 |   
       * |  -    15    17   19   20 |
       * |  -    -     18   20   21 |
       */
      final BandMatrix A = new BandMatrix(rowsNumber, bandwidth);
      A.setValue(0, 0, 10.0);
      A.setValue(0, 1, 11.0);
      A.setValue(0, 2, 12.0);

      A.setValue(1, 1, 13.0);
      A.setValue(1, 2, 14.0);
      A.setValue(1, 3, 15.0);

      A.setValue(2, 2, 16.0);
      A.setValue(2, 3, 17.0);
      A.setValue(2, 4, 18.0);

      A.setValue(3, 3, 19.0);
      A.setValue(3, 4, 20.0);

      A.setValue(4, 4, 21.0);

      final Matrix b = new Matrix(rowsNumber, 1);
      b.setValue(0, 0, 1);
      b.setValue(1, 0, 2);
      b.setValue(2, 0, 3);
      b.setValue(3, 0, 4);
      b.setValue(4, 0, 5);

      // ACT
      final Matrix x = A.times(b);

      // CHECK
      Assert.assertEquals(68.0, x.getValue(0, 0), 0.0);
      Assert.assertEquals(139.0, x.getValue(1, 0), 0.0);
      Assert.assertEquals(246.0, x.getValue(2, 0), 0.0);
      Assert.assertEquals(257.0, x.getValue(3, 0), 0.0);
      Assert.assertEquals(239.0, x.getValue(4, 0), 0.0);
   }

   @Test
   public void v1_times_7x5_SymetricBandMatrix_CorrectResult() {

      // ARRANGE
      final int rowsNumber = 7;
      final int bandwidth = 5;

      /*
       * |  10   11    12    -     -     -    -  |   
       * |  11   13    14    15    -     -    -  |  
       * |  12   14    16    17    18    -    -  |    
       * |  -    15    17    19    20    21   -  |    
       * |  -    -     18    20    22    23   24 |    
       * |  -    -     -     21    23    25   26 |   
       * |  -    -     -     -     24    26   27 |   
       */

      final BandMatrix A = new BandMatrix(rowsNumber, bandwidth);
      A.setValue(0, 0, 10.0);
      A.setValue(0, 1, 11.0);
      A.setValue(0, 2, 12.0);

      A.setValue(1, 1, 13.0);
      A.setValue(1, 2, 14.0);
      A.setValue(1, 3, 15.0);

      A.setValue(2, 2, 16.0);
      A.setValue(2, 3, 17.0);
      A.setValue(2, 4, 18.0);

      A.setValue(3, 3, 19.0);
      A.setValue(3, 4, 20.0);
      A.setValue(3, 5, 21.0);

      A.setValue(4, 4, 22.0);
      A.setValue(4, 5, 23.0);
      A.setValue(4, 6, 24.0);

      A.setValue(5, 5, 25.0);
      A.setValue(5, 6, 26.0);

      A.setValue(6, 6, 27.0);

      final Matrix b = new Matrix(rowsNumber, 1);
      b.setValue(0, 0, 1);
      b.setValue(1, 0, 2);
      b.setValue(2, 0, 3);
      b.setValue(3, 0, 4);
      b.setValue(4, 0, 5);
      b.setValue(5, 0, 6);
      b.setValue(6, 0, 7);

      // ACT
      final Matrix x = A.times(b);

      // CHECK
      Assert.assertEquals(68.0, x.getValue(0, 0), 0.0);
      Assert.assertEquals(139.0, x.getValue(1, 0), 0.0);
      Assert.assertEquals(246.0, x.getValue(2, 0), 0.0);
      Assert.assertEquals(383.0, x.getValue(3, 0), 0.0);
      Assert.assertEquals(550.0, x.getValue(4, 0), 0.0);
      Assert.assertEquals(531.0, x.getValue(5, 0), 0.0);
      Assert.assertEquals(465.0, x.getValue(6, 0), 0.0);
   }

   @Test
   public void v1_solveConjugateGradient__7x5_SymetricBandMatrix_CorrectResult() {

      // ARRANGE
      final int rowsNumber = 7;
      final int bandwidth = 5;

      /*
       * |  10   11    12    -     -     -    -  |   
       * |  11   13    14    15    -     -    -  |   
       * |  12   14    16    17    18    -    -  |    
       * |  -    15    17    19    20    21   -  |    
       * |  -    -     18    20    22    23   24 |   
       * |  -    -     -     21    23    25   26 |   
       * |  -    -     -     -     24    26   27 |   
       */

      final BandMatrix A = new BandMatrix(rowsNumber, bandwidth);
      A.setValue(0, 0, 10.0);
      A.setValue(0, 1, 11.0);
      A.setValue(0, 2, 12.0);

      A.setValue(1, 1, 13.0);
      A.setValue(1, 2, 14.0);
      A.setValue(1, 3, 15.0);

      A.setValue(2, 2, 16.0);
      A.setValue(2, 3, 17.0);
      A.setValue(2, 4, 18.0);

      A.setValue(3, 3, 19.0);
      A.setValue(3, 4, 20.0);
      A.setValue(3, 5, 21.0);

      A.setValue(4, 4, 22.0);
      A.setValue(4, 5, 23.0);
      A.setValue(4, 6, 24.0);

      A.setValue(5, 5, 25.0);
      A.setValue(5, 6, 26.0);

      A.setValue(6, 6, 27.0);

      final Matrix b = new Matrix(rowsNumber, 1);
      b.setValue(0, 0, 1);
      b.setValue(1, 0, 2);
      b.setValue(2, 0, 3);
      b.setValue(3, 0, 4);
      b.setValue(4, 0, 5);
      b.setValue(5, 0, 6);
      b.setValue(6, 0, 7);

      // ACT
      //
      // solve linear equation
      final Matrix x = BandMatrix.solveConjugateGradient(A, b);
      //
      // all elements of result should be zero 
      final Matrix result = A.times(x).minus(b);

      // CHECK
      for (int row = 0; row < 7; row++) {
         Assert.assertEquals(0.0, result.getValue(row, 0), 1E-1);
      }

      Assert.assertEquals(-1.6642476933655317f, x.getValue(0, 0), 1E-5);
      Assert.assertEquals(1.6427291984774461f, x.getValue(1, 0), 1E-5);
      Assert.assertEquals(-0.03562868746587197f, x.getValue(2, 0), 1E-5);
      Assert.assertEquals(-0.036663555242988616f, x.getValue(3, 0), 1E-5);
      Assert.assertEquals(0.06478349890678567f, x.getValue(4, 0), 1E-5);
      Assert.assertEquals(-0.9825863199384804f, x.getValue(5, 0), 1E-5);
      Assert.assertEquals(1.1478681609146024f, x.getValue(6, 0), 1E-5);
   }

   @Test
   public void v1_solveConjugateGradient_LargeRandomBandMatrix_Solved() {

      // ARRANGE
      final long start = System.currentTimeMillis();

      // ACT
      //
      // solve linear equation
      final Matrix x = BandMatrix.solveConjugateGradient(A, B);

      //
      // all elements of result should be zero 
      final Matrix result = A.times(x).minus(B);

      // CHECK
      for (int row = 0; row < Parameter.ROW_NUMBER; row++) {
         Assert.assertEquals(0.0, result.getValue(row, 0), 1E-3);
      }

      final long end = System.currentTimeMillis();
      System.out.print("\t" + (end - start));
   }

}