package org.protege.owl.server.api;

import org.protege.owl.server.api.exception.OWLServerException;
import org.protege.owl.server.api.server.ServerListener;
import org.protege.owl.server.api.server.ServerRequests;
import org.protege.owl.server.api.server.TransportHandler;

import edu.stanford.protege.metaproject.api.ClientConfiguration;
import edu.stanford.protege.metaproject.api.UserId;

public interface Server extends ServerRequests {

    void setTransport(TransportHandler transport) throws OWLServerException;

    void addServerListener(ServerListener listener);

    void removeServerListener(ServerListener listener);

    ClientConfiguration getClientConfiguration(UserId userId) throws OWLServerException;
}
