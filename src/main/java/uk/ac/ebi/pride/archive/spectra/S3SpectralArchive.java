package uk.ac.ebi.pride.archive.spectra;



import java.util.stream.Stream;

/**
 * The {@link S3SpectralArchive} is an interface that only provides methods to read one ArchivePSM after another
 * This interface and the classes behind allow to read an entire file without needs to index the entire file. For
 * indexed and random access to peak list files please use the JmzReader interface.
 *
 * @author yperez
 */
public class S3SpectralArchive implements ISpectralArchive {


    @Override
    public int getPSMCount() {
        return 0;
    }

    @Override
    public boolean savePSM(ArchivePSM psm, String key) {
        return false;
    }
}
