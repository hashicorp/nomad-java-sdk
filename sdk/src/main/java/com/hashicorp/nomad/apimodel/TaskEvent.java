package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class TaskEvent extends ApiObject {
    private String type;
    private long time;
    private boolean failsTask;
    private String restartReason;
    private String setupError;
    private String driverError;
    private String driverMessage;
    private int exitCode;
    private int signal;
    private String message;
    private String killReason;
    private long killTimeout;
    private String killError;
    private long startDelay;
    private String downloadError;
    private String validationError;
    private long diskLimit;
    private long diskSize;
    private String failedSibling;
    private String vaultError;
    private String taskSignalReason;
    private String taskSignal;

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public TaskEvent setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("Time")
    public long getTime() {
        return time;
    }

    public TaskEvent setTime(long time) {
        this.time = time;
        return this;
    }

    @JsonProperty("FailsTask")
    public boolean getFailsTask() {
        return failsTask;
    }

    public TaskEvent setFailsTask(boolean failsTask) {
        this.failsTask = failsTask;
        return this;
    }

    @JsonProperty("RestartReason")
    public String getRestartReason() {
        return restartReason;
    }

    public TaskEvent setRestartReason(String restartReason) {
        this.restartReason = restartReason;
        return this;
    }

    @JsonProperty("SetupError")
    public String getSetupError() {
        return setupError;
    }

    public TaskEvent setSetupError(String setupError) {
        this.setupError = setupError;
        return this;
    }

    @JsonProperty("DriverError")
    public String getDriverError() {
        return driverError;
    }

    public TaskEvent setDriverError(String driverError) {
        this.driverError = driverError;
        return this;
    }

    @JsonProperty("DriverMessage")
    public String getDriverMessage() {
        return driverMessage;
    }

    public TaskEvent setDriverMessage(String driverMessage) {
        this.driverMessage = driverMessage;
        return this;
    }

    @JsonProperty("ExitCode")
    public int getExitCode() {
        return exitCode;
    }

    public TaskEvent setExitCode(int exitCode) {
        this.exitCode = exitCode;
        return this;
    }

    @JsonProperty("Signal")
    public int getSignal() {
        return signal;
    }

    public TaskEvent setSignal(int signal) {
        this.signal = signal;
        return this;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    public TaskEvent setMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonProperty("KillReason")
    public String getKillReason() {
        return killReason;
    }

    public TaskEvent setKillReason(String killReason) {
        this.killReason = killReason;
        return this;
    }

    @JsonProperty("KillTimeout")
    public long getKillTimeout() {
        return killTimeout;
    }

    public TaskEvent setKillTimeout(long killTimeout) {
        this.killTimeout = killTimeout;
        return this;
    }

    @JsonProperty("KillError")
    public String getKillError() {
        return killError;
    }

    public TaskEvent setKillError(String killError) {
        this.killError = killError;
        return this;
    }

    @JsonProperty("StartDelay")
    public long getStartDelay() {
        return startDelay;
    }

    public TaskEvent setStartDelay(long startDelay) {
        this.startDelay = startDelay;
        return this;
    }

    @JsonProperty("DownloadError")
    public String getDownloadError() {
        return downloadError;
    }

    public TaskEvent setDownloadError(String downloadError) {
        this.downloadError = downloadError;
        return this;
    }

    @JsonProperty("ValidationError")
    public String getValidationError() {
        return validationError;
    }

    public TaskEvent setValidationError(String validationError) {
        this.validationError = validationError;
        return this;
    }

    @JsonProperty("DiskLimit")
    public long getDiskLimit() {
        return diskLimit;
    }

    public TaskEvent setDiskLimit(long diskLimit) {
        this.diskLimit = diskLimit;
        return this;
    }

    @JsonProperty("DiskSize")
    public long getDiskSize() {
        return diskSize;
    }

    public TaskEvent setDiskSize(long diskSize) {
        this.diskSize = diskSize;
        return this;
    }

    @JsonProperty("FailedSibling")
    public String getFailedSibling() {
        return failedSibling;
    }

    public TaskEvent setFailedSibling(String failedSibling) {
        this.failedSibling = failedSibling;
        return this;
    }

    @JsonProperty("VaultError")
    public String getVaultError() {
        return vaultError;
    }

    public TaskEvent setVaultError(String vaultError) {
        this.vaultError = vaultError;
        return this;
    }

    @JsonProperty("TaskSignalReason")
    public String getTaskSignalReason() {
        return taskSignalReason;
    }

    public TaskEvent setTaskSignalReason(String taskSignalReason) {
        this.taskSignalReason = taskSignalReason;
        return this;
    }

    @JsonProperty("TaskSignal")
    public String getTaskSignal() {
        return taskSignal;
    }

    public TaskEvent setTaskSignal(String taskSignal) {
        this.taskSignal = taskSignal;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static TaskEvent fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, TaskEvent.class);
    }

    public static List<TaskEvent> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, TaskEvent.class);
    }
}
