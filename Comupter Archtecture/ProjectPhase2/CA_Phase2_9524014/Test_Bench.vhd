--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   10:21:15 11/27/2018
-- Design Name:   
-- Module Name:   D:/9531066 & 9531030/az8/arya_division/TEST.vhd
-- Project Name:  arya_division
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: restoring_division
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY TEST IS
END TEST;
 
ARCHITECTURE behavior OF TEST IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT restoring_division
    PORT(
         M : IN  std_logic_vector(15 downto 0);
         Q : IN  std_logic_vector(31 downto 0);
         A : OUT  std_logic_vector(15 downto 0);
         Q_out : OUT  std_logic_vector(31 downto 0);
         clk : IN  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal M : std_logic_vector(15 downto 0) := (others => '0');
   signal Q : std_logic_vector(31 downto 0) := (others => '0');
   signal clk : std_logic := '0';

 	--Outputs
   signal A : std_logic_vector(15 downto 0);
   signal Q_out : std_logic_vector(31 downto 0);

   -- Clock period definitions
   constant clk_period : time := 2 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: restoring_division PORT MAP (
          M => M,
          Q => Q,
          A => A,
          Q_out => Q_out,
          clk => clk
        );
		  M <= "0000000000000011";
		  Q <= "00000000000000000000000000001111";

   -- Clock process definitions
   clk_process :process
   begin
		clk <= '0';
		wait for clk_period/2;
		clk <= '1';
		wait for clk_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100 ns.
      wait for 100 ns;	

      wait for clk_period*10;

      -- insert stimulus here 

      wait;
   end process;

END;
