/*
 * Copyright 2017 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.sleuthsamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;

public class MyTaskExecutionListener implements TaskExecutionListener {
	private static final Logger log = LoggerFactory.getLogger(MyTaskExecutionListener.class);

	private Tracer tracer;

	public MyTaskExecutionListener(Tracer tracer) {
		this.tracer = tracer;
	}

	@Override
	public void onTaskStartup(TaskExecution taskExecution) {
		log.info("<ACCEPTANCE_TEST> <TRACE:{}> Task Starting", tracer.currentSpan().context().traceId());
	}

	@Override
	public void onTaskEnd(TaskExecution taskExecution) {
		log.info("<ACCEPTANCE_TEST> <TRACE:{}> Task Ending", tracer.currentSpan().context().traceId());
	}

	@Override
	public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
		log.info("<ACCEPTANCE_TEST> <TRACE:{}> Task Failing", tracer.currentSpan().context().traceId());
	}
}
