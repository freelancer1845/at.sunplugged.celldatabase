
package at.sunplugged.celldatabase.rcp.handler;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.merge.BatchMerger;
import org.eclipse.emf.compare.merge.IBatchMerger;
import org.eclipse.emf.compare.merge.IMerger;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import at.sunplugged.celldatabase.rcp.dialogs.ConfirmRefreshDialog;

public class RefreshDatabaseHandler {
	private static final Logger LOG = LoggerFactory.getLogger(RefreshDatabaseHandler.class);

	@Execute
	public void execute(ModelDatabaseService databaseService, Shell shell) {
		Job job = new Job("Refreshing Database...") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Resource localRes = databaseService.getLocalResource();
				Resource remoteRes = databaseService.getRemoteResource();
				try {
					localRes.load(null);
				} catch (IOException e) {
					LOG.error("Failed to load Local Res...", e);
					return Status.CANCEL_STATUS;
				}
				try {
					remoteRes.load(null);
				} catch (IOException e) {
					LOG.error("Failed to load Remote Res...", e);
					return Status.CANCEL_STATUS;
				}

				IComparisonScope scope = new DefaultComparisonScope(remoteRes, localRes, null);
				Comparison comparison = EMFCompare.builder().build().compare(scope);

				List<Diff> differences = comparison.getDifferences();

				ConfirmRefreshDialog dialog = new ConfirmRefreshDialog(shell, differences);
				int result;
				Display.getDefault().syncExec(() -> {
					dialog.open();
				});
				result = dialog.getReturnCode();
				if (result == Window.OK) {
					IMerger.Registry mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();
					IBatchMerger merger = new BatchMerger(mergerRegistry);
					List<Diff> verifiedDiffs = dialog.getVerifiedDiffs();
					merger.copyAllLeftToRight(verifiedDiffs, new BasicMonitor());

				}

				remoteRes.unload();

				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.LONG);
		job.schedule();
	}

	@CanExecute
	public boolean canExecute(ModelDatabaseService databaseService) {
		return databaseService.isConnected();
	}

}