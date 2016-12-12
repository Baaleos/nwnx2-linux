package org.baaleos.systems.genetics;

import org.baaleos.systems.energy.Energy;

public class EnergyCostBinding {

	public EnergyCostBinding(Energy e, int Amount){
		this.setAmountToCharge(Amount);
		this.setEnergyToCharge(e);
	}
	
	public Energy getEnergyToCharge() {
		return EnergyToCharge;
	}
	public void setEnergyToCharge(Energy energyToCharge) {
		EnergyToCharge = energyToCharge;
	}

	public int getAmountToCharge() {
		return AmountToCharge;
	}

	public void setAmountToCharge(int amountToCharge) {
		AmountToCharge = amountToCharge;
	}

	private Energy EnergyToCharge;
	private int AmountToCharge;
	
}
