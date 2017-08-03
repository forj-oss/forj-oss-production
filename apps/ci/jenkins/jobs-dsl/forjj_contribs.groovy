multibranchPipelineJob('forjj-contribs') {
  description('Folder for Project forjj-contribs generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          apiUri('')
          includes('*')
          repoOwner('forj-oss')
          repository('forjj-contribs')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
