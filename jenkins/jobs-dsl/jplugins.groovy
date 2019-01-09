
multibranchPipelineJob('jplugins') {
  description('Folder for Project jplugins generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          repoOwner('forj-oss')
          scanCredentialsId('github-user')
          repository('jplugins')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
