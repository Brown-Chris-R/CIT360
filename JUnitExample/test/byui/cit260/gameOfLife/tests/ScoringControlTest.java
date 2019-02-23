/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.gameOfLife.tests;

import byui.cit260.gameOfLife.control.GameControl;
import byui.cit260.gameOfLife.exceptions.GameControlException;
import byui.cit260.gameOfLife.control.ScoringControl;
import byui.cit260.gameOfLife.exceptions.ItemControlException;
import byui.cit260.gameOfLife.exceptions.ScoringControlException;
import byui.cit260.gameOfLife.model.Game;
import byui.cit260.gameOfLife.model.Player;
import byui.cit260.gameOfLife.view.ErrorView;
import cit260.game.of.life.team.b.CIT260GameOfLifeTeamB;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cbrown
 */
public class ScoringControlTest {
    
    public ScoringControlTest() {
    }

    /**
     * Test of scoreAdolescenceSchoolChoice method, of class ScoringControl.
     */
    @Test
    public void testScoreAdolescenceSchoolChoice() {

        // Create test player
        Player testPlayer;
        try {
            testPlayer = GameControl.createPlayer("Test");
            // Create test Game object
            try {
                GameControl.createNewGame(testPlayer);
            } catch (GameControlException ge) {
                ErrorView.display(this.getClass().getName(), ge.getMessage());
                return;
            } catch (Throwable te){
                ErrorView.display(this.getClass().getName(), te.getMessage());
            }
        } catch (GameControlException ge) {
            ErrorView.display(this.getClass().getName(), ge.getMessage());
        }

        System.out.println("scoreAdolescenceSchoolChoice");

        /**********************************************************************
         * Test Case #1
         *********************************************************************/
        System.out.println("Test Case #1");
        char choice = 'A';
        ScoringControl sc = new ScoringControl();

        int expResult = -10;
        int result;

        try {
            result = sc.scoreAdolescenceSchoolChoice(choice);
            assertEquals(expResult, result);
        }
        catch (Exception e){
            fail(e.getMessage());
        }

        /**********************************************************************
         * Test Case #2
         *********************************************************************/
        System.out.println("Test Case #2");
        choice = 'B';
        expResult = 5;
        try {
            result = sc.scoreAdolescenceSchoolChoice(choice);
            assertEquals(expResult, result);
        }
        catch (Exception e){
            fail(e.getMessage());
        }
        /**********************************************************************
         * Test Case #3
         *********************************************************************/
        System.out.println("Test Case #3");
        choice = 'C';
        expResult = 5;
        try {
            result = sc.scoreAdolescenceSchoolChoice(choice);
            assertEquals(expResult, result);
        }
        catch (Exception e){
            fail(e.getMessage());
        }
        /**********************************************************************
         * Test Case #4
         *********************************************************************/
        System.out.println("Test Case #4");
        choice = 'D';
        expResult = -1;
        try {
            result = sc.scoreAdolescenceSchoolChoice(choice);
            fail();
        }
        catch (ScoringControlException e){
            assertEquals("Choice D is not a valid choice. Must be an 'A', 'B' or 'C'.", e.getMessage());
        }
        catch (ItemControlException e){
            fail(e.getMessage());
        }
        /**********************************************************************
         * Test Case #5
         *********************************************************************/
        System.out.println("Test Case #5");
        choice = '1';
        expResult = -1;
        try {
            result = sc.scoreAdolescenceSchoolChoice(choice);
            fail();
        }
        catch (ScoringControlException e){
            assertEquals("Choice 1 is not a valid choice. Must be an 'A', 'B' or 'C'.", e.getMessage());
        }
        catch (ItemControlException e){
            fail(e.getMessage());
        }
        /**********************************************************************
         * Test Case #6
         *********************************************************************/
        System.out.println("Test Case #6");
        choice = '@';
        expResult = -1;
        try {
            result = sc.scoreAdolescenceSchoolChoice(choice);
            fail();
        }
        catch (ScoringControlException e){
            assertEquals("Choice @ is not a valid choice. Must be an 'A', 'B' or 'C'.", e.getMessage());
        }
        catch (ItemControlException e){
            fail(e.getMessage());
        }
        /**********************************************************************
         * Test Case #7
         *********************************************************************/
        System.out.println("Test Case #7");
        choice = 'B';
        expResult = 5;
        try {
            result = sc.scoreAdolescenceSchoolChoice(choice);
            assertEquals(expResult, result);
        }
        catch (Exception e){
            fail(e.getMessage());
        }
        /**********************************************************************
         * Test Case #8
         *********************************************************************/
        System.out.println("Test Case #8");
        choice = 'B';
        expResult = 5;
        try {
            result = sc.scoreAdolescenceSchoolChoice(choice);
            assertEquals(expResult, result);
        }
        catch (Exception e){
            fail(e.getMessage());
        }
        /**********************************************************************
         * Test Case #9
         *********************************************************************/
        System.out.println("Test Case #9");
        choice = 'B';
        expResult = 5;
        try {
            result = sc.scoreAdolescenceSchoolChoice(choice);
            assertEquals(expResult, result);
        }
        catch (Exception e){
            fail(e.getMessage());
        }
        /**********************************************************************
         * Test Case #10
         *********************************************************************/
        System.out.println("Test Case #10");
        choice = 'C';
        expResult = 5;
        try {
            result = sc.scoreAdolescenceSchoolChoice(choice);
            assertEquals(expResult, result);
        }
        catch (Exception e){
            fail(e.getMessage());
        }
        /**********************************************************************
         * Test Case #11
         *********************************************************************/
        System.out.println("Test Case #11");
        choice = 'C';
        expResult = 5;
        try {
            result = sc.scoreAdolescenceSchoolChoice(choice);
            assertEquals(expResult, result);
        }
        catch (Exception e){
                fail(e.getMessage());
                }
        /**********************************************************************
         * Test Case #12
         *********************************************************************/
        System.out.println("Test Case #12");
        choice = 'C';
        expResult = 5;
        try {
            result = sc.scoreAdolescenceSchoolChoice(choice);
            assertEquals(expResult, result);
        }
        catch (Exception e){
            fail(e.getMessage());
        }
    }

    /**
     * Test of calcOperation method, of class ScoringControl.
     */
    @Test
    public void testCalcOperation() {

        // Create test player
        Player testPlayer;
        try {
            testPlayer = GameControl.createPlayer("Test");
            // Create test Game object
            try {
                GameControl.createNewGame(testPlayer);
            } catch (GameControlException ge) {
                ErrorView.display(this.getClass().getName(), ge.getMessage());
                return;
            } catch (Throwable te){
                ErrorView.display(this.getClass().getName(), te.getMessage());
            }
        } catch (GameControlException ge) {
            ErrorView.display(this.getClass().getName(), ge.getMessage());
        }

        System.out.println("calcOperation");

        /**********************************************************************
         * Test Case #1
         *********************************************************************/
        System.out.println("Test Case #1");       
        float hospitalBillAmt = 29400.0F;
        float insuranceDeductibleAmt = 350.0F;
        float insuranceCoverageAmt = 0.75F;
        ScoringControl instance = new ScoringControl();
        float result;
        float expResult = 7612.5F;

        try {
            result = instance.calcOperation(hospitalBillAmt, insuranceDeductibleAmt, insuranceCoverageAmt);
            assertEquals(expResult, result, 0.01);
        }
        catch (Exception e){
            fail(e.getMessage());
        }
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        /**********************************************************************
         * Test Case #2
         *********************************************************************/
        System.out.println("Test Case #2");       
        hospitalBillAmt = 0.0F;
        insuranceDeductibleAmt = 400.0F;
        insuranceCoverageAmt = 0.5F;
        expResult = -1.0F;

        try {
            result = instance.calcOperation(hospitalBillAmt, insuranceDeductibleAmt, insuranceCoverageAmt);
            fail();
        }
        catch (ScoringControlException e){
            assertEquals("Hospital Bill Amount must be greater than zero.", e.getMessage());
        }
        /**********************************************************************
         * Test Case #3
         *********************************************************************/
        System.out.println("Test Case #3");       
        hospitalBillAmt = 31000.0F;
        insuranceDeductibleAmt = 400.0F;
        insuranceCoverageAmt = 0.5F;
        expResult = -1.0F;

        try {
            result = instance.calcOperation(hospitalBillAmt, insuranceDeductibleAmt, insuranceCoverageAmt);
            fail();
        }
        catch (ScoringControlException e){
            assertEquals("Hospital Bill Amount must be less than $30,000.", e.getMessage());
        }
        /**********************************************************************
         * Test Case #4
         *********************************************************************/
        System.out.println("Test Case #4");       
        hospitalBillAmt = 1000.0F;
        insuranceDeductibleAmt = -500.0F;
        insuranceCoverageAmt = 0.5F;
        expResult = -1.0F;

        try {
            result = instance.calcOperation(hospitalBillAmt, insuranceDeductibleAmt, insuranceCoverageAmt);
            fail();
        }
        catch (ScoringControlException e){
            assertEquals("Insurance Deductible Amount cannot be less than zero", e.getMessage());
        }        /**********************************************************************
         * Test Case #5
         *********************************************************************/
        System.out.println("Test Case #5");       
        hospitalBillAmt = 1000.0F;
        insuranceDeductibleAmt = 1000.0F;
        insuranceCoverageAmt = 0.5F;
        expResult = -1.0F;

        try {
            result = instance.calcOperation(hospitalBillAmt, insuranceDeductibleAmt, insuranceCoverageAmt);
            fail();
        }
        catch (ScoringControlException e){
            assertEquals("Insurance Deductible Amount cannot be greater than %500", e.getMessage());
        }        /**********************************************************************
        /**********************************************************************
         * Test Case #6
         *********************************************************************/
        System.out.println("Test Case #6");       
        hospitalBillAmt = 1000.0F;
        insuranceDeductibleAmt = 400.0F;
        insuranceCoverageAmt = -0.25F;
        expResult = -1.0F;

        try {
            result = instance.calcOperation(hospitalBillAmt, insuranceDeductibleAmt, insuranceCoverageAmt);
            fail();
        }
        catch (ScoringControlException e){
            assertEquals("Insurance Coverage Amount cannot be less than zero", e.getMessage());
        }
        /**********************************************************************
        /**********************************************************************
         * Test Case #7
         *********************************************************************/
        System.out.println("Test Case #7");       
        hospitalBillAmt = 1000.0F;
        insuranceDeductibleAmt = 400.0F;
        insuranceCoverageAmt = 1.1F;
        expResult = -1.0F;

        try {
            result = instance.calcOperation(hospitalBillAmt, insuranceDeductibleAmt, insuranceCoverageAmt);
            fail();
        }
        catch (ScoringControlException e){
            assertEquals("Insurance Coverage Amount cannot be greater than 100%", e.getMessage());
        }
        /**********************************************************************
         * Test Case #8
         *********************************************************************/
        System.out.println("Test Case #8");       
        hospitalBillAmt = 1.0F;
        insuranceDeductibleAmt = 0.0F;
        insuranceCoverageAmt = 0.0F;
        expResult = 1.0F;

        try {
            result = instance.calcOperation(hospitalBillAmt, insuranceDeductibleAmt, insuranceCoverageAmt);

            assertEquals(expResult, result, 0.01);
        }
        catch (Exception e){
            fail(e.getMessage());
        }
        /**********************************************************************
         * Test Case #9
         *********************************************************************/
        System.out.println("Test Case #9");       
        hospitalBillAmt = 30000.0F;
        insuranceDeductibleAmt = 500.0F;
        insuranceCoverageAmt = 0.8F;
        expResult = 6400.0F;

        try {
            result = instance.calcOperation(hospitalBillAmt, insuranceDeductibleAmt, insuranceCoverageAmt);

            assertEquals(expResult, result, 0.01);
        }
        catch (Exception e){
            fail(e.getMessage());
        }

    }

}
