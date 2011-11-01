package org.encog.ml.bayesian.query;

import java.util.List;
import java.util.Map;

import org.encog.ml.bayesian.BayesianEvent;
import org.encog.ml.bayesian.BayesianNetwork;
import org.encog.ml.bayesian.EventType;
import org.encog.ml.bayesian.query.sample.EventState;

/**
 * A Bayesian query. This is used to query a Bayesian network and determine a
 * the probability of an output, given some input. The input is called evidence,
 * and the output is the outcome. This results in a final probability of the
 * output being what you specified.
 * 
 * You can easily change the events between evidence and outcome, this allows
 * the Bayesian network to be queried in nearly any way.  It is also possible to
 * omit missing evidence to handle missing data.
 */
public interface BayesianQuery {

	/**
	 * @return The Bayesian network that we are using this query for.
	 */
	BayesianNetwork getNetwork();

	/**
	 * @return A mapping of events to event states.
	 */
	Map<BayesianEvent, EventState> getEvents();

	/**
	 * @return The evidence events (inputs).
	 */
	List<BayesianEvent> getEvidenceEvents();

	/**
	 * @return The outcome events (outputs).
	 */
	List<BayesianEvent> getOutcomeEvents();

	/**
	 * Reset all event types back to hidden.
	 */
	void reset();

	/**
	 * Define an event type to be either hidden(default), evidence(input) or
	 * outcome (output).
	 * 
	 * @param event
	 *            The event to define.
	 * @param et
	 *            THe new event type.
	 */
	void defineEventType(BayesianEvent event, EventType et);

	/**
	 * Get the event state for a given event.
	 * @param event The event to get the state for.
	 * @return The event state.
	 */
	EventState getEventState(BayesianEvent event);

	/**
	 * Get the event type.
	 * @param event The event to check.
	 * @return The current event type for this event.
	 */
	EventType getEventType(BayesianEvent event);

	/**
	 * Set the event value to a boolean.
	 * @param event The event.
	 * @param b The value.
	 */
	void setEventValue(BayesianEvent event, boolean b);

	/**
	 * Set the event value as a class item.
	 * @param event The event to set.
	 * @param d An integer class item.
	 */
	void setEventValue(BayesianEvent event, double d);

	/**
	 * Return a string that represents this query as a probability "problem".
	 * @return
	 */
	String getProblem();

	/**
	 * Execute the query.
	 */
	void execute();

	/**
	 * @return Obtains the probability after execute has been called.
	 */
	double getProbability();
	
	void finalizeStructure();
	
	/**
	 * Called to locate the evidence and outcome events.
	 */
	public void locateEventTypes();
}
