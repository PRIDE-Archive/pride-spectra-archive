package uk.ac.ebi.pride.archive.spectra;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import uk.ac.ebi.pride.tools.jmzreader.model.IndexElement;
import uk.ac.ebi.pride.tools.jmzreader.model.Spectrum;

/**
 * Parser for a peak list formats.
 * 
 * <b>NOTE:</b> Every class implementing the ISpectralArchive
 * interface should contain the following static function:
 * public static ArchivePSM getIndexedSpectrum(File sourcefile, IndexElement indexElement)
 *
 * @author ypriverol
 *
 */
public interface ISpectralArchive {
	/**
	 * Returns the number of spectra in the given
	 * file / directory.
	 * @return
	 */
    int getPSMCount();
	
	boolean savePSM(ArchivePSM psm, String key);
}
