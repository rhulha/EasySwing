package net.raysforge.easyswing;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EasyFileDropTarget extends DropTargetAdapter {
	
	List<FileDropListener> fileDropListenerList = new ArrayList<FileDropListener>(); 

	public EasyFileDropTarget(Component c) {
		new DropTarget(c, this);
	}
	
	public void addFileDropListener(FileDropListener fdl)
	{
		fileDropListenerList.add(fdl);
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
			dtde.acceptDrop(dtde.getDropAction());
			try {
				@SuppressWarnings("unchecked")
				List<File> list = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
				for (FileDropListener fdl : fileDropListenerList) {
					fdl.filesDropped(list);
				}
			} catch (UnsupportedFlavorException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	} // drop

}
