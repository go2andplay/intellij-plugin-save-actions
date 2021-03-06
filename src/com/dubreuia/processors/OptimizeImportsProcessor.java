package com.dubreuia.processors;

import com.dubreuia.model.Storage;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;

import static com.dubreuia.model.Action.organizeImports;
import static com.dubreuia.processors.ProcessorMessage.toStringBuilder;

class OptimizeImportsProcessor extends com.intellij.codeInsight.actions.OptimizeImportsProcessor implements Processor {

    private static final String ID = "OptimizeImports";

    private final Storage storage;

    OptimizeImportsProcessor(Project project, PsiFile file, Storage storage) {
        super(project, file);
        this.storage = storage;
    }

    @Override
    public void writeToFile() {
        if (storage.isEnabled(organizeImports)) {
            ApplicationManager.getApplication().invokeLater(new Runnable() {
                @Override
                public void run() {
                    OptimizeImportsProcessor.this.run();
                }
            }, myProject.getDisposed());
        }
    }

    @Override
    public String toString() {
        return toStringBuilder(ID, storage.isEnabled(organizeImports));
    }

}
