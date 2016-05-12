package org.protege.editor.owl.server.versioning.api;

import org.protege.editor.owl.server.versioning.ChangeMetadata;
import org.protege.editor.owl.server.versioning.DocumentRevision;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;

import java.util.List;
import java.util.SortedMap;

/**
 * This is a lightweight class that captures the collection of changes to a
 * collection of ontology documents between two revisions. So for example, using
 * Matthew's binary data format, the data in this class will consist of a start
 * revision, an end revision and the file containing the change set. When the
 * data is serialized essentially a selection of the change file is sent and it
 * is reconstituted on the remote side.
 * 
 * @author tredmond
 */
public interface ChangeHistory {

    String CHANGE_DOCUMENT_EXTENSION = ".history";

    /**
     * Get the start revision of this collection of changes.
     * 
     * @return OntologyDocumentRevision
     */
    DocumentRevision getStartRevision();

    /**
     * Get the end revision of this collection of changes.
     * 
     * @return OntologyDocumentRevision
     */
    DocumentRevision getEndRevision();

    
    void addRevisionBundle(DocumentRevision revision, ChangeMetadata metadata, List<OWLOntologyChange> changes);

    /**
     * Returns the ChangeMetaData (user, date of change) for the change document
     * at a given revision.
     * <p>
     * It should be non-null for all revisions from the start revision of the
     * change document (inclusive) to the end revision of the document
     * (exclusive). Passing any other revision id in leads to unknown results.
     * 
     * @param revision
     *            revision
     * @return ChangeMetaData
     */
    ChangeMetadata getChangeMetadataForRevision(DocumentRevision revision);

    /**
     * Gets the list of changes associated with this document and associates
     * them with the ontology.
     * <p>
     * The changes returned are guaranteed to be minimal. That is if an axiom is
     * added it will not be added again or removed. Similarly for annotations
     * and imports. The SetOntologyID change can only occur once.
     * <p>
     * The minimality simplifies processing of the returned changes. For example
     * the order of the changes is no longer important.
     * 
     * @param ontology
     *            ontology
     * @return List of OWLOntologyChange
     */
    List<OWLOntologyChange> getChanges(OWLOntology ontology);

    boolean isEmpty();

    List<List<OWLOntologyChange>> getRevisionsList();

    SortedMap<DocumentRevision, ChangeMetadata> getMetadataMap();
}
