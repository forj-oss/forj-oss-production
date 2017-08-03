multibranchPipelineJob('forjj-examples') {
  description('Folder for Project forjj-examples generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      git {
          remote('')
          includes('*')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
