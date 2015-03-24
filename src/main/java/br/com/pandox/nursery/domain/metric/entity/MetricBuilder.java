package br.com.pandox.nursery.domain.metric.entity;

public class MetricBuilder {
	private String name;
	private String type;
	private Integer timeInterval;

	public MetricBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public MetricBuilder setType(String type) {
		this.type = type;
		return this;
	}

	public MetricBuilder setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
		return this;
	}

	public MetricEntity build() {
		return new MetricEntity(name, type, timeInterval);
	}
}
