----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    08:35:07 11/27/2018 
-- Design Name: 
-- Module Name:    restoring_division - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity restoring_division is
Port(
M : in std_logic_vector(15 downto 0);
Q : in std_logic_vector(31 downto 0);
A : out std_logic_vector(15 downto 0);
Q_out : out std_logic_vector(31 downto 0);
clk : in std_logic
);
end entity restoring_division;

architecture Behavioral of restoring_division is
	type state_t is (shift_left_prime,A_minus_M,shift_left , restore_A , initial_state , split_AQ,assign_Q0,decrease_N,check_if_N_is_ZERO,finish_state);
	signal state : state_t := initial_state;
	signal next_state : state_t := shift_left;
	signal AQ : std_logic_vector(47 downto 0);
	signal A_temp : std_logic_vector(15 downto 0);
	signal Q_temp : std_logic_vector(31 downto 0);
	signal N : std_logic_vector(5 downto 0);
begin
		CMB : process(state)
			begin
				case state is
				
					when initial_state =>
						N <= "100000";
						A_temp <= "0000000000000000";
						--AQ <= "0000000000000000" & Q;
						AQ <= "0000000000000000" & "00000000000000000000000000001011";
						--Q_temp <= Q ;
						Q_temp <= "00000000000000000000000000001111" ;
						next_state <= shift_left;
						
						
					when shift_left =>
						AQ <= A_temp & Q_temp;
						next_state <= shift_left_prime;
														
					when shift_left_prime => 
					   AQ <= AQ(46 downto 0) & '0';
						next_state <= split_AQ;
					
					when split_AQ =>
						A_temp <= AQ(47 downto 32);
						Q_temp <= AQ(31 downto 0);
						next_state <= A_minus_M;
						
					when A_minus_M => 
						A_temp  <= std_logic_vector(unsigned(A_temp) - unsigned(M)); 
						next_state <= assign_Q0;

						
					when assign_Q0 => 
						if(A_temp(15) = '1') then
							Q_temp(0) <= '0';
							next_state <= restore_A;
						else 
							Q_temp(0) <= '1';
						   next_state <= decrease_N;
						end if;
						
				
					when restore_A => 
						A_temp <= std_logic_vector(unsigned(A_temp) + unsigned(M)); 
						next_state <= decrease_N;

					when decrease_N =>
					 	N <=std_logic_vector(unsigned(N)  - "0001");
						next_state <= check_if_N_is_ZERO;
					
					
					when check_if_N_is_ZERO =>
					
					   if(N = "000000") then 
						    next_state <= finish_state;
						else 
							 next_state <= shift_left;
					   end if;
						
						
				   when finish_state => 
					    Q_out <= Q_temp;
						 A <= A_temp;
						 
				end case;
			end process;
		REG : process(clk)
			begin
				if(clk'event and clk = '1') then
					state <= next_state;
				end if;
		end process;
end Behavioral;

