package uk.ac.ebi.pride.archive.spectra;

import java.util.Map;

import uk.ac.ebi.pride.archive.dataprovider.param.CvParamProvider;
import uk.ac.ebi.pride.integration.submission.service.IndexSpectrumService;
import uk.ac.ebi.pride.tools.jmzreader.model.impl.ParamGroup;

/**
 * Represents a spectrum in the
 * parsed peak list file.
 * @author jg
 *
 */
public interface ArchivePSM extends ISpectrum {

	/** Unified Spectra Identifier **/
	String getUSI();

	/** Returns the shown sequence (without modifications). */
	String getSequence();

	/** Returns the charge of the item. */
	int getCharge();

	/** Getter for the mass to charge value */
	double getMass();

	/** Getter for the missed cleavages */
	int getMissedCleavages();

	/** Getter for the retention time. */
	Double getRetentionTime();

	/** Getter for the source ID. */
	String getSourceID();

	/** Getter for the spectrum title. */
	String getSpectrumTitle();

	/** Getter for the delta mass. */
	double getDeltaMass();

	/** Getter for the modifications of this item. */
	Map<Integer, CvParamProvider> getModifications();
}
