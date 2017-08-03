multibranchPipelineJob('forjj-flows') {
  description('Folder for Project forjj-flows generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          apiUri('')
          includes('*')
          repoOwner('forj-oss')
          repository('forjj-flows')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
