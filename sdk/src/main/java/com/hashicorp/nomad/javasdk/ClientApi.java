package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AllocFileInfo;
import com.hashicorp.nomad.apimodel.AllocResourceUsage;
import com.hashicorp.nomad.apimodel.HostStats;
import org.apache.http.HttpHost;
import org.apache.http.client.utils.URIBuilder;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * API for interacting with a particular Nomad client,
 * exposing the functionality of the {@code /v1/client/…} endpoints of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class ClientApi extends ApiBase {
    private final HttpHost address;

    ClientApi(final NomadApiClient apiClient, final HttpHost address) {
        super(apiClient);
        this.address = address;
    }

    /*
    +func (a *Allocations) GC(alloc *Allocation, q *QueryOptions) error {
+       nodeClient, err := a.client.GetNodeClient(alloc.NodeID, q)
+       if err != nil {
+               return err
+       }
+
+       var resp struct{}
+       _, err = nodeClient.query("/v1/client/allocation/"+alloc.ID+"/gc", &resp, nil)
+       return err
+}
     */

    /**
     * Queries the actual resource usage of the client node.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/client-stats.html">{@code GET /v1/client/stats}</a>
     */
    public NomadResponse<HostStats> stats() throws IOException, NomadException {
        return executePlain(get(uri(address, "/v1/client/stats")), NomadJson.parserFor(HostStats.class));
    }

    /**
     * Queries the resource usage of an allocation running on the client node.
     *
     * @param allocationId ID of the allocation to lookup
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/client-allocation-stats.html">{@code GET /v1/client/allocation/{ID}/stats}</a>
     */
    public NomadResponse<AllocResourceUsage> stats(String allocationId) throws IOException, NomadException {
        return executePlain(
                get(uri(address, "/v1/client/allocation/" + allocationId + "/stats")),
                NomadJson.parserFor(AllocResourceUsage.class));
    }

    /**
     * Reads the contents of a file in an allocation directory.
     *
     * @param allocationId ID of the allocation that produced the file
     * @param path         the path of the file relative to the root of the allocation directory
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/client-fs.html">{@code GET /v1/client/fs/cat/{Allocation-ID}}</a>
     */
    public NomadResponse<String> cat(String allocationId, String path) throws IOException, NomadException {
        return executePlain(
                get(uri(address, "/v1/client/fs/cat/" + allocationId)
                        .addParameter("path", path)),
                ValueExtractor.RAW_STRING);
    }

    /**
     * Reads the contents of a file in an allocation directory at a particular offset.
     *
     * @param allocationId ID of the allocation that produced the file
     * @param path         the path of the file relative to the root of the allocation directory
     * @param offset       the byte offset from where content will be read
     * @param limit        the number of bytes to read from the offset
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/client-fs.html">{@code GET /v1/client/fs/readat/{Allocation-ID}}</a>
     */
    public NomadResponse<String> readAt(String allocationId, String path, long offset, long limit)
            throws IOException, NomadException {
        return executePlain(
                get(uri(address, "/v1/client/fs/readat/" + allocationId)
                        .addParameter("path", path)
                        .addParameter("offset", Long.toString(offset))
                        .addParameter("limit", Long.toString(limit))),
                ValueExtractor.RAW_STRING);
    }

    /**
     * Streams the contents of a file in an allocation directory.
     * <p>
     * Note that unless there is an error, the streaming connection to the client node will remain open until the
     * stream's {@link FramedStream#close()} method is invoked, even if the allocation has completed.
     * <p>
     * To retrieve the contents of a file without the complexity of streaming, use the {@link #cat} method instead.
     *
     * @param allocationId the ID of the allocation that produced the file
     * @param path         the path of the file relative to the root of the allocation directory
     * @return a StreamAborter that can be used to abort the stream before it finishes.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/client-fs.html">{@code GET /v1/client/fs/stream/{Allocation-ID}}</a>
     */
    public FramedStream stream(final String allocationId, final String path) throws IOException, NomadException {
        return stream(allocationId, path, null);
    }

    /**
     * Streams the contents of a file in an allocation directory.
     * <p>
     * Note that unless there is an error, the streaming connection to the client node will remain open until the
     * stream's {@link FramedStream#close()} method is invoked, even if the allocation has completed.
     * <p>
     * To retrieve the contents of a file without the complexity of streaming, use the {@link #cat} method instead.
     *
     * @param allocationId the ID of the allocation that produced the file
     * @param path         the path of the file relative to the root of the allocation directory
     * @param offset       the byte offset at which to start streaming
     * @return a StreamAborter that can be used to abort the stream before it finishes.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/client-fs.html">{@code GET /v1/client/fs/stream/{Allocation-ID}}</a>
     */
    public FramedStream stream(final String allocationId,
                               final String path,
                               @Nullable final Long offset) throws IOException, NomadException {

        return stream(allocationId, path, offset, null);
    }

    /**
     * Streams the contents of a file in an allocation directory.
     * <p>
     * Note that unless there is an error, the streaming connection to the client node will remain open until the
     * stream's {@link FramedStream#close()} method is invoked, even if the allocation has completed.
     * <p>
     * To retrieve the contents of a file without the complexity of streaming, use the {@link #cat} method instead.
     *
     * @param allocationId the ID of the allocation that produced the file
     * @param path         the path of the file relative to the root of the allocation directory
     * @param offset       the byte offset at which to start streaming
     * @param origin       null or "start" indicate the the offset is relative to the beginning of the file,
     *                     "end" indicates that the offset is relative to end of the file.
     * @return a StreamAborter that can be used to abort the stream before it finishes.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/client-fs.html">{@code GET /v1/client/fs/stream/{Allocation-ID}}</a>
     */
    public FramedStream stream(
            final String allocationId,
            final String path,
            @Nullable final Long offset,
            @Nullable final String origin) throws IOException, NomadException {

        final URIBuilder uri = uri(address, "/v1/client/fs/stream/" + allocationId)
                .addParameter("path", path);
        if (offset != null)
            uri.addParameter("offset", Long.toString(offset));
        if (origin != null)
            uri.addParameter("origin", origin);
        return apiClient.executeFramedStream(get(uri), null);
    }

    /**
     * Initiates garbage collection of an allocation.
     *
     * @param allocId ID of the allocation
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/system.html">{@code PUT /v1/system/gc}</a>
     */
    public NomadResponse<Void> garbageCollect(String allocId) throws IOException, NomadException {
        return garbageCollect(allocId, null);
    }

    /**
     * Initiates garbage collection of an allocation.
     *
     * @param allocId ID of the allocation
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/system.html">{@code PUT /v1/system/gc}</a>
     */
    public NomadResponse<Void> garbageCollect(String allocId, @Nullable QueryOptions<Void> options)
            throws IOException, NomadException {
        return executeServerQuery("/v1/client/allocation/" + allocId + "/gc", null, null);
    }

    /**
     * Streams a task's stdout or stderr log.
     * <p>
     * Note that if follow is true, then unless there is an error, the streaming connection to the client node will
     * remain open until the stream's {@link FramedStream#close()} method is invoked, even if the allocation
     * has completed.
     *
     * @param allocationId the ID of the allocation that produced the log
     * @param taskName     the name of the task that produced the log
     * @param follow       if true, the stream remains open even after the end of the log has been reached
     * @param logType      "stdout" or "stderr"
     * @return a StreamAborter that can be used to abort the stream before it finishes.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/client-fs.html">{@code GET /v1/client/fs/logs/{Allocation-ID}}</a>
     */
    public InputStream logs(
            final String allocationId,
            final String taskName,
            final boolean follow,
            final String logType
    ) throws IOException, NomadException {
        return apiClient.executeRawStream(get(
                uri(address, "/v1/client/fs/logs/" + allocationId)
                        .addParameter("task", taskName)
                        .addParameter("plain", "true")
                        .addParameter("follow", Boolean.toString(follow))
                        .addParameter("type", logType)
        ), null);
    }

    /**
     * Streams a task's stdout or stderr log.
     * <p>
     * Note that if follow is true, then unless there is an error, the streaming connection to the client node will
     * remain open until the stream's {@link FramedStream#close()} method is invoked, even if the allocation
     * has completed.
     *
     * @param allocationId the ID of the allocation that produced the log
     * @param taskName     the name of the task that produced the log
     * @param follow       if true, the stream remains open even after the end of the log has been reached
     * @param logType      "stdout" or "stderr"
     * @return a StreamAborter that can be used to abort the stream before it finishes.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/client-fs.html">{@code GET /v1/client/fs/logs/{Allocation-ID}}</a>
     */
    public FramedStream logsAsFrames(
            final String allocationId,
            final String taskName,
            final boolean follow,
            final String logType
    ) throws IOException, NomadException {
        return apiClient.executeFramedStream(get(
                uri(address, "/v1/client/fs/logs/" + allocationId)
                        .addParameter("task", taskName)
                        .addParameter("follow", Boolean.toString(follow))
                        .addParameter("type", logType)
        ), null);
    }

    /**
     * Streams a task's stdout or stderr log.
     * <p>
     * Note that if follow is true, then unless there is an error, the streaming connection to the client node will
     * remain open until the stream's {@link FramedStream#close()} method is invoked, even if the allocation
     * has completed.
     *
     * @param allocationId the ID of the allocation that produced the log
     * @param taskName     the name of the task that produced the log
     * @param follow       if true, the stream remains open even after the end of the log has been reached
     * @param logType      "stdout" or "stderr"
     * @param offset       Specifies the offset to start streaming from
     * @return a StreamAborter that can be used to abort the stream before it finishes.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/client-fs.html">{@code GET /v1/client/fs/logs/{Allocation-ID}}</a>
     */
    public FramedStream logsAsFrames(
            final String allocationId,
            final String taskName,
            final boolean follow,
            final String logType,
            final long offset
    ) throws IOException, NomadException {
        return apiClient.executeFramedStream(get(
                uri(address, "/v1/client/fs/logs/" + allocationId)
                        .addParameter("task", taskName)
                        .addParameter("follow", Boolean.toString(follow))
                        .addParameter("type", logType)
                        .addParameter("offset", Long.toString(offset))
        ), null);
    }

    /**
     * Lists the files in an allocation directory.
     *
     * @param allocationId ID of the allocation that owns the directory
     * @param path         the path relative to the root of the allocation directory
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/client-fs.html">{@code GET /v1/client/fs/ls/{Allocation-ID}}</a>
     */
    public NomadResponse<List<AllocFileInfo>> ls(String allocationId, String path) throws IOException, NomadException {
        return executePlain(
                get(uri(address, "/v1/client/fs/ls/" + allocationId)
                        .addParameter("path", path)),
                NomadJson.parserForListOf(AllocFileInfo.class));
    }

    /**
     * Stat a file in an allocation directory.
     *
     * @param allocationId ID of the allocation that owns the file
     * @param path         the path relative to the root of the allocation directory
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/client-fs.html">{@code GET /v1/client/fs/stat/{Allocation-ID}}</a>
     */
    public NomadResponse<AllocFileInfo> stat(String allocationId, String path) throws IOException, NomadException {
        return executePlain(get(
                uri(address, "/v1/client/fs/stat/" + allocationId)
                        .addParameter("path", path)),
                NomadJson.parserFor(AllocFileInfo.class));
    }

}
