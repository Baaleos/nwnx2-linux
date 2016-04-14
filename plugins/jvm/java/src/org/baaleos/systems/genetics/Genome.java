package org.baaleos.systems.genetics;

import java.util.ArrayList;

import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;


public class Genome extends ArrayList<Gene>  {

	
	/**
	 * Returns a collection of Genes for the object used in the constructor
	 * Will work for players, creatures and potentially placeables.
	 * @param player
	 */
	public Genome(NWObject player){
		int AmountOfGenesOnCreature = NWScript.getLocalInt(player,"creature_gene_count_");
		int i;
		for(i=1;i<=AmountOfGenesOnCreature;i++){
			int GeneID = NWScript.getLocalInt(player,"creature_genome_storage_"+i);
			if(GeneID != 0){
				//Gene geneToApply = new Gene(GeneID);
				Gene geneToApply = Gene.getGeneByID(GeneID);
				this.add(geneToApply);
			}
		}
	}
	private static final String INHUMAN_ID_TO_NAME = "inhuman_id_to_name";
	private static final String INHUMAN_ABILITY_ROSTER = "INHUMAN_ABILITY_MAPPING_";
	
	
	/**
	 * Returns a collection of Genes, that belong to a cached Genome defined on the module.
	 * This is for when you have defined a Genome in NSS, and wish it to be applied to multiple creatures
	 * or even creation of a subrace engine.
	 * Eg: All Vampires have this genome
	 * All Drow have this genome etc
	 * @param genomeID
	 */
	public Genome(int genomeID){
		NWObject oMod = NWObject.MODULE;
		String genomeName = NWScript.getLocalString(oMod, INHUMAN_ID_TO_NAME+"_"+genomeID);
		int PowersForGenome = NWScript.getLocalInt(oMod,INHUMAN_ABILITY_ROSTER+genomeID+"_Count");
		int iPower =0;
	    for(iPower = 1;iPower<=PowersForGenome;iPower++){
	        int iGene = NWScript.getLocalInt(oMod,INHUMAN_ABILITY_ROSTER+genomeID+"_"+iPower);
	        if(iGene != 0){
	        	//Gene geneToApply = new Gene(iGene);
	        	Gene geneToApply = Gene.getGeneByID(iGene);
				this.add(geneToApply);
	        }
	    }
	}
	
	
	
}
