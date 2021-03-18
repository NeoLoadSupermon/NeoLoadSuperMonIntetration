package com.neotys.supermon.datamodel;
import com.neotys.ascode.api.v3.client.model.PostCustomEventRequest;
import com.neotys.supermon.common.MySupermonUtils;
import com.neotys.supermon.common.NeoLoadException;

import java.math.BigDecimal;
import java.text.ParseException;

import static com.neotys.supermon.conf.Constants.EVENT_SOURCE_MYSUPERMON;

//type : DABASEEVENT | REGRESSION | ERROR….
//						description : ""
//						status :
//						metricName :"" (nullable)
//						links: "",
//						deviation:  nullable
//						value:
//						oldValue:
//						definition : {
//							   errorThrueshold :
//							   warningThreshold :
//						},
//						startTimestamp:,
//						endTimestamp:,
public class MySuperMonEvent {
    MySuperMonEventType type;
    String description;
    String status;
    String metrixName;
    String links;
    Double deviation;
    Double value;
    Double oldValue;
    MySuperMonThresholdDefinition definition;
    String starttimestamp;
    String endtimestamp;
    String nleventid;

    public MySuperMonEvent(MySuperMonEventType type, String description, String status, String metricName, String links, Double deviation, Double value, Double oldValue, MySuperMonThresholdDefinition definition, String startTimestamp, String endTimestamp) {
        this.type = type;
        this.description = description;
        this.status = status;
        this.metrixName = metricName;
        this.links = links;
        this.deviation = deviation;
        this.value = value;
        this.oldValue = oldValue;
        this.definition = definition;
        this.starttimestamp = startTimestamp;
        this.endtimestamp = endTimestamp;
    }

    public PostCustomEventRequest toNeoLoadEvent(String instance) throws NeoLoadException {
        try {
                PostCustomEventRequest postCustomEventRequest=new PostCustomEventRequest();
                postCustomEventRequest.setUrl(this.links);
                postCustomEventRequest.setSource(EVENT_SOURCE_MYSUPERMON);
                postCustomEventRequest.setTimeframe(PostCustomEventRequest.TimeframeEnum.TIME_RANGE);
                postCustomEventRequest.setDescription(generateEventDescription(instance));
                postCustomEventRequest.setFullname(generateFullName(instance));
                postCustomEventRequest.code(type.toString());

                long converte_date= 0;
                converte_date = MySupermonUtils.convertDate(this.starttimestamp);
                 if(converte_date>0)
                        postCustomEventRequest.setStartTimestamp(BigDecimal.valueOf(converte_date / 1000));
                if(this.endtimestamp!=null)
                {
                    converte_date = MySupermonUtils.convertDate(this.endtimestamp);
                    if(converte_date>0)
                        postCustomEventRequest.setEndTimestamp(BigDecimal.valueOf(converte_date));
                }

                return postCustomEventRequest;

        } catch (ParseException e) {
            throw new NeoLoadException("The event date are not in the right format");
        }


    }

    private String generateFullName(String instance)
    {
        return EVENT_SOURCE_MYSUPERMON+" "+type.toString()+" on "+instance+" on "+this.metrixName;

    }

    private String generateEventDescription(String instance)
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(EVENT_SOURCE_MYSUPERMON+" "+type.toString()+" on "+instance+" on "+this.metrixName);
        stringBuilder.append("Current value : "+ String.valueOf(this.value)+"\n");
        stringBuilder.append("Previous/BaseLine value " +String.valueOf(this.oldValue)+"\n");
        if(definition!=null) {
            stringBuilder.append("\n Definition of the event : \n");
            if(definition.getErrorThrueshold()!=null)
                stringBuilder.append("\t - error " + definition.getErrorThrueshold());
            if(definition.getWarningThreshold() !=null)
                stringBuilder.append("\t - warning " + definition.getWarningThreshold());

        }

        return stringBuilder.toString();

    }


    public String getNleventid() {
        return nleventid;
    }

    public void setNleventid(String nleventid) {
        this.nleventid = nleventid;
    }

    public MySuperMonEventType getType() {
        return type;
    }

    public void setType(MySuperMonEventType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetricName() {
        return metrixName;
    }

    public void setMetricName(String metricName) {
        this.metrixName = metricName;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public Double getDeviation() {
        return deviation;
    }

    public void setDeviation(Double deviation) {
        this.deviation = deviation;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getOldValue() {
        return oldValue;
    }

    public void setOldValue(Double oldValue) {
        this.oldValue = oldValue;
    }

    public MySuperMonThresholdDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(MySuperMonThresholdDefinition definition) {
        this.definition = definition;
    }

    public String getStartTimestamp() {
        return starttimestamp;
    }

    public void setStartTimestamp(String startTimestamp) {
        this.starttimestamp = startTimestamp;
    }

    public String getEndTimestamp() {
        return endtimestamp;
    }

    public void setEndTimestamp(String endTimestamp) {
        this.endtimestamp = endTimestamp;
    }
}
