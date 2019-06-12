package uk.ac.ebi.pride.archive.spectra.services;

import uk.ac.ebi.pride.archive.dataprovider.data.peptide.PSMProvider;

import java.io.IOException;

/**
 * Parser for a peak list formats.
 * 
 * <b>NOTE:</b> Every class implementing the ISpectralArchive
 * interface should contain the following static function:
 * public static IArchivePSM getIndexedSpectrum(File sourcefile, IndexElement indexElement)
 *
 * @author ypriverol
 *
 */
public interface ISpectralArchive {

	PSMProvider writePSM(String usi, PSMProvider psm) throws IOException;
}
