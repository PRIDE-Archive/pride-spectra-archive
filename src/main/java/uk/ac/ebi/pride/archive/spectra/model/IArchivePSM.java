package uk.ac.ebi.pride.archive.spectra.model;

import java.util.Map;

import uk.ac.ebi.pride.archive.dataprovider.param.CvParamProvider;

/**
 * Represents a spectrum in the
 * parsed peak list file.
 * @author jg
 *
 */
public interface IArchivePSM extends ISpectrum {

	/** Returns the shown sequence (without modifications). */
	String getSequence();

	/** Getter for the mass to charge value */
	double getTheoreticalMass();

	/** Getter for the missed cleavages */
	int getMissedCleavages();

	/** Getter for the source ID. */
	String getSourceID();

	/** Getter for the spectrum title. */
	String getSpectrumTitle();

	/** Getter for the delta mass. */
	double getDeltaMass();

	/** Getter for the modifications of this item. */
	Map<Integer, CvParamProvider> getModifications();
}
