
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import at.sunplugged.database.datareader.DataReaderHelper;
import datamodel.CellGroup;
import datamodel.CellResult;

public class CalculateCellResultFromPythonHandler {
	@Execute
	public void execute(Shell parent, @Named("TreeViewer") TreeViewer treeViewer, ModelDatabaseService databaseService,
			@Named("editingDomain") EditingDomain editingDomain) {
		FileDialog dialog = new FileDialog(parent, SWT.MULTI | SWT.OPEN);
		String dir = dialog.open();
		if (dir != null) {
			Path root = Paths.get(dir);
			dir = root.getParent().toString();
			String[] fileNames = dialog.getFileNames();
			List<File> files = new ArrayList<>();
			for (String fileName : fileNames) {
				files.add(new File(dir + "\\" + fileName));
			}

			Job job = new Job("caculateCellResults") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					List<CellResult> results = DataReaderHelper.readAndCalculateFile(files);
					if (results.size() > 0) {
						Display.getDefault().asyncExec(() -> {
							Command cmd = AddCommand.create(editingDomain,
									((CellGroup) treeViewer.getStructuredSelection().getFirstElement()), null, results);
							editingDomain.getCommandStack().execute(cmd);
						});

					}
					return Status.OK_STATUS;
				}
			};

			job.setPriority(Job.LONG);
			job.schedule();

		}

	}

	@CanExecute
	public boolean canExecute(@Named("TreeViewer") TreeViewer treeViewer) {
		return HandlerHelper.isSingleSelected(treeViewer.getStructuredSelection(), CellGroup.class);
	}

}