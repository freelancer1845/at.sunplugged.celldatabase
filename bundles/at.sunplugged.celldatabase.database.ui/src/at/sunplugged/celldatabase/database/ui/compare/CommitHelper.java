package at.sunplugged.celldatabase.database.ui.compare;

import java.io.IOException;
import java.util.List;

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
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommitHelper {

	private static final Logger LOG = LoggerFactory.getLogger(CommitHelper.class);

	public static void commit(Shell shell, Resource localRes, Resource remoteRes) throws IOException {
		try {
			localRes.load(null);
		} catch (IOException e) {
			LOG.error("Failed to load LocalRes in Commit Helper!", e);
			throw e;
		}
		try {
			remoteRes.load(null);
		} catch (IOException e) {
			LOG.error("Failed to load RemoteRes in Commit Helper!", e);
			throw e;
		}

		IComparisonScope scope = new DefaultComparisonScope(localRes, remoteRes, null);
		Comparison comparison = EMFCompare.builder().build().compare(scope);
		List<Diff> differences = comparison.getDifferences();

		// ICompareEditingDomain editingDomain =
		// EMFCompareEditingDomain.create(localRes, remoteRes, null);
		// AdapterFactory adapterFactory = new
		// ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		// EMFCompareConfiguration configuration = new EMFCompareConfiguration(new
		// CompareConfiguration());
		//
		// CompareEditorInput input = new ComparisonEditorInput(configuration,
		// comparison, editingDomain, adapterFactory);
		//
		// UIJob uiJob = new UIJob("Commit Dialog") {
		// public org.eclipse.core.runtime.IStatus
		// runInUIThread(org.eclipse.core.runtime.IProgressMonitor arg0) {
		// CompareUI.openCompareDialog(input); // or CompareUI.openCompareEditor(input);
		// return Status.OK_STATUS;
		// };
		// };
		// uiJob.schedule();

		IMerger.Registry mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();
		IBatchMerger merger = new BatchMerger(mergerRegistry);
		merger.copyAllLeftToRight(differences, new BasicMonitor());

		remoteRes.save(null);

	}

	private CommitHelper() {

	}
}
