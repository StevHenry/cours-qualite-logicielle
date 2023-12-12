Feature: Plug a new machine
	A new coffee machine just arrived.
	The user plugs it and is connected to the power grid
	Scenario: A working coffee machine is being connected to a live power grid
		Given A new coffee machine with 0.10 L of minimal capacity, 3.0 L of maximal capacity, 600.0 L per H of water flow for the pump
		When The user plugs the new machine
		And The machine is not out of order
		Then The machine is connected to the power grid