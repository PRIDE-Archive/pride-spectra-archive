package uk.ac.ebi.pride.archive.spectra.services;

import uk.ac.ebi.pride.archive.dataprovider.data.peptide.PSMProvider;
import uk.ac.ebi.pride.archive.dataprovider.data.spectra.ArchiveSpectrum;

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

	ArchiveSpectrum writePSM(String usi, ArchiveSpectrum psm) throws Exception;
}
