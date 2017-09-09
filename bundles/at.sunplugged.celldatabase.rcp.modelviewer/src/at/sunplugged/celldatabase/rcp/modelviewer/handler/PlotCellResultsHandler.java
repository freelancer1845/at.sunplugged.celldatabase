
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import java.util.List;

import javax.inject.Named;

import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import at.sunplugged.celldatabase.plotting.PlotHelper;
import datamodel.CellResult;

public class PlotCellResultsHandler {
	@Execute
	public void execute(@Named("TreeViewer") TreeViewer viewer) {
		IStructuredSelection selection = ((TreeViewer) viewer).getStructuredSelection();

		@SuppressWarnings("unchecked") // Checked in canExecute...
		List<CellResult> cellResults = selection.toList();

		Job plotJob = new Job("PlotJob") {
			protected org.eclipse.core.runtime.IStatus run(org.eclipse.core.runtime.IProgressMonitor monitor) {
				PlotHelper.plotCellResults(cellResults);
				return Status.OK_STATUS;
			};
		};
		plotJob.setPriority(Job.INTERACTIVE);
		plotJob.schedule();
	}

	@SuppressWarnings("unchecked") // Actual checking...
	@CanExecute
	public boolean canExecute(@Named("TreeViewer") TreeViewer viewer) {
		IStructuredSelection selection = ((TreeViewer) viewer).getStructuredSelection();

		return selection.toList().stream().allMatch(arg -> (arg instanceof CellResult));
	}

}